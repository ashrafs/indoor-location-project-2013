package com.brian.android.mymap;

import com.brian.android.mymap.Position;

public class Position {

	public int x;
	
	public int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position mult(float ratio) {
		int x = (int) (this.x * ratio);
		int y = (int) (this.y * ratio);
		return new Position(x, y);
	}
	
	public Position sub(Position position) {
        return new Position(x - position.x, y - position.y);
	}	
	
	public Position add(Position position) {
        return new Position(x + position.x, y + position.y);
	}	
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
