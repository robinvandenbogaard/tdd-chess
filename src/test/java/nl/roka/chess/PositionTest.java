package nl.roka.chess;

import org.junit.jupiter.api.Test;

import static nl.roka.chess.MoveDirection.Down;
import static nl.roka.chess.MoveDirection.Up;
import static nl.roka.chess.Position.root;
import static nl.roka.chess.Position.vector;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class PositionTest {

	@Test
	void subtract() {
		var from = vector(1, 1);
		var to = vector(1, 2);
		var expected = vector(0, -1);

		assertThat(from.subtract(to), is(expected));
	}

	@Test
	void normalizeUp() {
		var from = vector(3, 1);
		var expected = vector(3, 1);

		assertThat(from.normalize(Up), is(expected));
	}

	@Test
	void normalizeDown() {
		var from = vector(3, 1);
		var expected = vector(-3, 1);

		assertThat(from.normalize(Down), is(expected));
	}

	@Test
	void forwardUpAddsARow() {
		var from = root();
		var expected = vector(1, 0);

		assertThat(from.forward(Up), is(expected));
	}

	@Test
	void forwardDownSubtractsARow() {
		var from = root();
		var expected = vector(-1, 0);

		assertThat(from.forward(Down), is(expected));
	}

	@Test
	void backwardUpSubtractsARow() {
		var from = root();
		var expected = vector(-1, 0);

		assertThat(from.backward(Up), is(expected));
	}

	@Test
	void backwardDownAddsARow() {
		var from = root();
		var expected = vector(1, 0);

		assertThat(from.backward(Down), is(expected));
	}
}