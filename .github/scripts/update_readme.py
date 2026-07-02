#!/usr/bin/env python3
"""Generate README solved-problem table from src/*/Solution.java."""

from __future__ import annotations

import re
from pathlib import Path
from urllib.parse import quote


ROOT = Path(__file__).resolve().parents[2]
SRC_DIR = ROOT / "src"
README = ROOT / "README.md"

START_MARKER = "<!-- SOLVED:START -->"
END_MARKER = "<!-- SOLVED:END -->"


def escape_markdown_table(text: str) -> str:
    return text.replace("|", r"\|")


def markdown_link(path: Path) -> str:
    relative_path = path.relative_to(ROOT).as_posix()
    return quote(relative_path, safe="/._-()")


def split_problem_name(directory_name: str) -> tuple[str | None, str]:
    """Return optional Programmers lesson id and display name.

    Supports folder names like:
    - 유연근무제_388351
    - 유연근무제-388351
    - p340213_동영상재생기
    - 동영상재생기
    """
    name_first_match = re.match(r"^(.+?)[-_ ]+(\d+)$", directory_name)
    if name_first_match:
        display_name, lesson_id = name_first_match.groups()
        return lesson_id, display_name

    package_safe_id_first_match = re.match(r"^p(\d+)[-_ ]+(.+)$", directory_name)
    if package_safe_id_first_match:
        lesson_id, display_name = package_safe_id_first_match.groups()
        return lesson_id, display_name

    return None, directory_name


def find_solutions() -> list[Path]:
    if not SRC_DIR.exists():
        return []

    return sorted(SRC_DIR.glob("*/Solution.java"), key=lambda path: path.parent.name)


def build_generated_section() -> str:
    solutions = find_solutions()
    lines = [
        START_MARKER,
        "",
        f"> 현재 풀이한 문제 개수 : {len(solutions)}",
        "",
        "| 번호 | 문제 | 풀이 |",
        "|---:|---|---|",
    ]

    for index, solution_path in enumerate(solutions, start=1):
        lesson_id, display_name = split_problem_name(solution_path.parent.name)
        problem_name = escape_markdown_table(display_name)

        if lesson_id is not None:
            problem_name = (
                f"[{problem_name}]"
                f"(https://school.programmers.co.kr/learn/courses/30/lessons/{lesson_id})"
            )

        solution_link = markdown_link(solution_path)
        lines.append(f"| {index} | {problem_name} | [Java]({solution_link}) |")

    lines.extend(["", END_MARKER])
    return "\n".join(lines)


def render_readme() -> str:
    generated_section = build_generated_section()

    if not README.exists():
        return "\n".join(
            [
                "# Programmers",
                "",
                "프로그래머스 코딩테스트 풀이 기록입니다.",
                "",
                generated_section,
                "",
            ]
        )

    current = README.read_text(encoding="utf-8")
    start_index = current.find(START_MARKER)
    end_index = current.rfind(END_MARKER)

    if start_index != -1 and end_index != -1 and start_index < end_index:
        prefix = current[:start_index].rstrip()
        suffix = current[end_index + len(END_MARKER) :].strip()
        parts = [prefix, generated_section]

        if suffix:
            parts.append(suffix)

        return "\n\n".join(parts).rstrip() + "\n"

    return current.rstrip() + "\n\n" + generated_section + "\n"


def main() -> None:
    next_content = render_readme()

    if README.exists() and README.read_text(encoding="utf-8") == next_content:
        return

    README.write_text(next_content, encoding="utf-8")


if __name__ == "__main__":
    main()
