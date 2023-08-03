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




}
