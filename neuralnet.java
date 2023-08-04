public class neuralnet {
    
    private int inputNodes;
    private int hiddenNodes;
    private int outputNodes;

    private matrix ihWeights;
    private matrix hhWeights;
    private matrix hoWeights;

    public neuralnet(int i, int h, int o)
    {
        inputNodes = i;
        hiddenNodes = h;
        outputNodes = o;

        ihWeights = new matrix(hiddenNodes, inputNodes + 1);
        hhWeights = new matrix(hiddenNodes, hiddenNodes + 1);
        hoWeights = new matrix(outputNodes, hiddenNodes + 1);
    }

    public void mutate(double rate)
    {
        ihWeights.mutate(rate);
        hhWeights.mutate(rate);
        hoWeights.mutate(rate);
    }

    public double[] output(double[] inputArr)
    {
        matrix input = matrix.arrToMatrix(inputArr);
        matrix inputBias = input.addBias();

        matrix hiddenInput = ihWeights.dot(inputBias);
        matrix hiddenOutput = hiddenInput.activate();
        matrix hiddenOutputBias = hiddenOutput.addBias();

        matrix hiddenInputL2 = hhWeights.dot(hiddenOutputBias);
        matrix hiddenOutputL2 = hiddenInputL2.activate();
        matrix hiddenOutputL2Bias = hiddenOutputL2.addBias();

        matrix outputInput = hoWeights.dot(hiddenOutputL2Bias);
        matrix output = outputInput.activate();

        return output.toArray();

    }

    public neuralnet crossover(neuralnet other)
    {
        neuralnet child = new neuralnet(inputNodes, hiddenNodes, hiddenNodes);

        child.ihWeights = ihWeights.crossover(other.ihWeights);
        child.hhWeights = hhWeights.crossover(other.hhWeights);
        child.hoWeights = hoWeights.crossover(other.hoWeights);

        return child;
    }

    public neuralnet clone()
    {
        neuralnet other = new neuralnet(inputNodes, hiddenNodes, hiddenNodes);

        other.ihWeights = ihWeights.clone();
        other.hhWeights = hhWeights.clone();
        other.hoWeights = hoWeights.clone();

        return other;
    }

    




}