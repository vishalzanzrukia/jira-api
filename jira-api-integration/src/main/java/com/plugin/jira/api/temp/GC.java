package com.plugin.jira.api.temp;

import javassist.ClassPool;
import javassist.CtClass;

public class GC {
	GC t;
	static int i = 1;

	public static void main(String args[]) {
		GC t1 = new GC();
		GC t2 = new GC();
		GC t3 = new GC();

		// No Object Is Eligible for GC

		t1.t = t2; // No Object Is Eligible for GC
		t2.t = t3; // No Object Is Eligible for GC
		t3.t = t1; // No Object Is Eligible for GC

		t1 = null;
		// No Object Is Eligible for GC (t3.t still has a reference to t1)

		t2 = null;
		// No Object Is Eligible for GC (t3.t.t still has a reference to t2)

		t3 = null;
		// All the 3 Object Is Eligible for GC (None of them have a reference.
		// only the variable t of the objects are referring each other in a
		// rounded fashion forming the Island of objects with out any external
		// reference)
	}

	protected void finalize() {
		System.out.println("Garbage collected from object" + i);
		i++;
	}

	void testLoopWithDifferentTypeOfGC() {

	}
}

/**
 * @author Vishal.Zanzrukia
 *
 */
class PermGenErrorGenerator {

	public static void main(String[] args) throws Exception {
		try {
			for (int i = 0; i < 100_000_000; i++) {
				generate("com.test.GenerateOutOfMemoryError" + i);
			}
		} catch (VirtualMachineError e) {
			System.err.println(e.getMessage() + " Exceed");
//			Thread.sleep(60*1000);
		}
	}

	public static Class generate(String name) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass ctClass = pool.makeClass(name);
		// ctClass.stopPruning(true);
		// ctClass.defrost();
		// ctClass.writeFile();
		return ctClass.toClass();
	}
}