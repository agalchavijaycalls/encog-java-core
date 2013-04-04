package org.encog.ml.prg.generator;

import java.util.Random;

import org.encog.ml.prg.EncogProgram;
import org.encog.ml.prg.EncogProgramContext;
import org.encog.ml.prg.ProgramNode;
import org.encog.ml.prg.expvalue.ValueType;

public class RampedHalfAndHalf extends AbstractPrgGenerator {
	
	private final int minDepth;
	private final PrgFullGenerator fullGenerator;
	private final PrgGrowGenerator growGenerator;
		
	public RampedHalfAndHalf(EncogProgramContext theContext, int theMinDepth, int theMaxDepth) {
		super(theContext, theMaxDepth);
		this.minDepth = theMinDepth;
		
		this.fullGenerator = new PrgFullGenerator(theContext, theMaxDepth);
		this.growGenerator = new PrgGrowGenerator(theContext, theMaxDepth);
	}

	@Override
	public ProgramNode createNode(Random rnd, EncogProgram program, int depthRemaining, ValueType t) {
		int actualDepthRemaining = depthRemaining;
				
		if( rnd.nextBoolean() ) {
			return this.fullGenerator.createNode(rnd, program, actualDepthRemaining, t);
		} else {
			return this.growGenerator.createNode(rnd, program, actualDepthRemaining, t);
		}
	}

	public int getMinDepth() {
		return minDepth;
	}
	
	public int determineMaxDepth(Random rnd) {
		int range = getMaxDepth() - this.minDepth;
		return rnd.nextInt(range) + this.minDepth;
	}
	
	
}