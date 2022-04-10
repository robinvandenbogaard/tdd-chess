package nl.roka.chess.move;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import static nl.roka.chess.move.MoveDirection.Down;
import static nl.roka.chess.move.MoveDirection.Up;
import static nl.roka.chess.move.Position.*;
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

	@Test
	void findPositionsBetweenHorizontally() {
		var from = root();
		var to = vector(0, 2);
		var expected = List.of(vector(0, 1));

		assertThat(from.positionsBetween(to), is(expected));
		assertThat(to.positionsBetween(from), is(expected));
	}

	@Test
	void findPositionsBetweenVertically() {
		var from = root();
		var to = vector(2, 0);
		var expected = List.of(vector(1, 0));

		assertThat(from.positionsBetween(to), is(expected));
		assertThat(to.positionsBetween(from), is(expected));
	}

	@Test
	void findPositionsBetweenDiagonally() {
		var from = root();
		var to = vector(2, 2);
		var expected = List.of(vector(1, 1));

		assertThat(from.positionsBetween(to), is(expected));
		assertThat(to.positionsBetween(from), is(expected));
	}

	@Test
	void findCornerToCornerHorizontally() {
		var from = position("a1");
		var to = position("h1");
		var expected = List.of(position("b1"), position("c1"), position("d1"), position("e1"), position("f1"),
							   position("g1"));

		assertThat(from.positionsBetween(to), is(expected));
		assertThat(to.positionsBetween(from), is(expected.reverse()));
	}

	@Test
	void findCornerToCornerVertically() {
		var from = position("a1");
		var to = position("a8");
		var expected = List.of(position("a2"), position("a3"), position("a4"), position("a5"), position("a6"),
							   position("a7"));

		assertThat(from.positionsBetween(to), is(expected));
		assertThat(to.positionsBetween(from), is(expected.reverse()));
	}

	@Test
	void findCornerToCornerDiagonally() {
		var from = position("a1");
		var to = position("h8");
		var expected = List.of(position("b2"), position("c3"), position("d4"), position("e5"), position("f6"),
							   position("g7"));

		assertThat(from.positionsBetween(to), is(expected));
		assertThat(to.positionsBetween(from), is(expected.reverse()));
	}
}