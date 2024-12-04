package io.github.lhug.adventofcode.twentytwentyfour.fourth;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WordSearchTest {

    private static final String SMALL_INPUT = """
..X...
.SAMX.
.A..A.
XMAS.S
.X....
""";

    private static final String X_MAS_INPUT = """
.M.S......
..A..MSMS.
.M.S.MAA..
..A.ASMSM.
.M.S.M....
..........
S.S.S.S.S.
.A.A.A.A..
M.M.M.M.M.
..........
""";

    private static final String LARGE_INPUT = """
MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
MSAMASMSMX
XMASAMXAMM
XXAMMXXAMA
SMSMSASXSS
SAXAMASAAA
MAMMMXMMMM
MXMXAXMASX
""";

    private WordSearch sut = new WordSearch(SMALL_INPUT);

    @Test
    void initializes_word_search_with_2d_grid() {
        sut = new WordSearch(LARGE_INPUT);

        assertThat(sut).extracting("grid")
                .isEqualTo(new char[][]{
                                new char[]{'M', 'M', 'M', 'S', 'X', 'X', 'M', 'A', 'S', 'M'},
                                new char[]{'M', 'S', 'A', 'M', 'X', 'M', 'S', 'M', 'S', 'A'},
                                new char[]{'A', 'M', 'X', 'S', 'X', 'M', 'A', 'A', 'M', 'M'},
                                new char[]{'M', 'S', 'A', 'M', 'A', 'S', 'M', 'S', 'M', 'X'},
                                new char[]{'X', 'M', 'A', 'S', 'A', 'M', 'X', 'A', 'M', 'M'},
                                new char[]{'X', 'X', 'A', 'M', 'M', 'X', 'X', 'A', 'M', 'A'},
                                new char[]{'S', 'M', 'S', 'M', 'S', 'A', 'S', 'X', 'S', 'S'},
                                new char[]{'S', 'A', 'X', 'A', 'M', 'A', 'S', 'A', 'A', 'A'},
                                new char[]{'M', 'A', 'M', 'M', 'M', 'X', 'M', 'M', 'M', 'M'},
                                new char[]{'M', 'X', 'M', 'X', 'A', 'X', 'M', 'A', 'S', 'X'}
                        }
                );
    }

    @Test
    void counts_xmas_occurrences() {
        assertThat(sut.countXmas()).isEqualTo(4);
    }

    @Test
    void counts_crossed_mas_occurrences() {
        sut = new WordSearch(X_MAS_INPUT);

        var result = sut.countX_mas();

        assertThat(result).isEqualTo(9);
    }
}
