package model;

import java.util.Map;

public abstract class Personality {
	public Map<String, AttributeVector> vectors;
	
	public AttributeVector getVector(Card c) {
		if (vectors == null) {
			initializeVectors();
		}
		return vectors.get(c.toString());
	}
	
	public abstract void initializeVectors();
}