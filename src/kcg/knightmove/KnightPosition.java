package kcg.knightmove;

/**
 * this class represents a cell on the board
 * encoded by its x-y position
 *
 * @author J. Chen
 * */
public final class KnightPosition {
	// row number
	private final int x;
	// column number
	private final int y;

	public KnightPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int xPosition() {
		return x;
	}

	public int yPosition() {
		return y;
	}


	@Override
	public boolean equals(Object o) {
		if (!(o instanceof KnightPosition)) {
			return false;
		}
		KnightPosition p = (KnightPosition)o;
		return p.x == x && p.y == y;
	}

	@Override
	public String toString() {
		return String.format("[x:%s, y:%s]", x,y);
	}


	private volatile int hashCode = 0;
	@Override
	public int hashCode() {
		if (hashCode == 0) {
			int result = 17;
			result = 37*result + x;
			result = 37*result + y;
			hashCode = result;
		}
		return hashCode;
	}

}