package model;

import java.util.Map;

public abstract class Personality {
	public Map<Card, AttributeVector> vectors;
	
	public AttributeVector getVector(Card c) {
		if (vectors == null) {
			initializeVectors();
		}
		return vectors.get(c);
	}
	
	public abstract void initializeVectors();
}
