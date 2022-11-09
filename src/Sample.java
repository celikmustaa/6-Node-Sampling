import java.util.Random;

public class Sample {

    // 6-Vertex Samplings
    public static double cycleTENSample(int k, BipartiteGraph graph){
        double final_result = 0;
        for(int i=0; i<k; i++){
            Cycle cycle = graph.getRandomCycle();
            long count = ExactCount.cycleTENCount(cycle, graph);

            final_result += count;
        }
        return final_result/k;
    }
}

