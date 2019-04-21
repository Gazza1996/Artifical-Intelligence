package ie.gmit.sw.ai;

import org.encog.Encog;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;


public class EncogNN {

	// taken from in class labs for NN
	
	// names set from imports for encog
	private static BasicNetwork network;
	private static MLDataSet trainingSet;
	private static ResilientPropagation train;

	private static double[][] data = { // Strength, Venom, Hiding
			{ 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 }, { 0, 0, 1, 1 }, { 1, 1, 0, 0 },
			{ 1, 1, 1, 0 }, { 1, 1, 0, 1 }, { 1, 1, 1, 1 }, { 0, 1, 1, 0 }, { 0, 1, 0, 1 }, { 0, 0, 0, 0 } };

	private static double[][] expected = { // Chase, Attack, Hide
			{ 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 }, { 0, 0, 0, 1 }, { 1, 0, 0, 0 },
			{ 0, 1, 0, 0 }, { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 }, { 0, 0, 1, 0 } };

	public void go() {
		/*
		 * steps to carry out NN work
		 */

		// Step 1 Declare Network Topology
		network = createNetwork();

		// Step 2 Create the training data set
		trainingSet = new BasicMLDataSet(data, expected);

		// Step 3 Train the NN
		setTrain(trainNetwork(network, trainingSet));

		// Step 4 Test the NN
		test(trainingSet, network);

		// Step 5 shutdown NN
		Encog.getInstance().shutdown();

	}

	/*
	 * create the NN
	 */
	BasicNetwork createNetwork() {
		// network set up
		BasicNetwork network = new BasicNetwork();
		// adding the layers
		network.addLayer(new BasicLayer(null, true, 4));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 2));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 4));
		network.getStructure().finalizeStructure();
		network.reset();

		return network;

	}
	/*
	 * Training
	 */
	ResilientPropagation trainNetwork(BasicNetwork network, MLDataSet trainingSet) {
		// trainig the NN to recongise some stuff
		ResilientPropagation train = new ResilientPropagation(network, trainingSet);
		// errors
		double minError = 0.1;
		// iterators
		int epoch = 1;

		do {
			train.iteration();
			epoch++;
		} while (train.getError() > minError);

		train.finishTraining(); // finish

		System.out.println("[INFO] NN finished in... " + epoch + " epochs with error ()= " + train.getError());

		return train;
	}

	void test(MLDataSet trainingSet, BasicNetwork network) {

		System.out.println("[INFO] Testing...");
		// test training data
		for (MLDataPair pair : trainingSet) {

			MLData output = network.compute(pair.getInput());

			System.out.println(pair.getInput().getData(0) + "," + pair.getInput().getData(1) + ", X = "
					+ (int) Math.round(output.getData(0)) + ", Y = " + (int) pair.getIdeal().getData(0));
		}

	}

	public static int getState(int strength, int venom, int playerPos, int hidePos) {
		// vars
		double[] input = { strength, venom, playerPos, hidePos };

		MLData data = new BasicMLData(input);

		int value = network.classify(data);

		return value;
	}

	public static ResilientPropagation getTrain() {
		return train;
	}

	public static void setTrain(ResilientPropagation train) {
		EncogNN.train = train;
	}

}
