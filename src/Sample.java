import java.util.Random;

public class Sample {
    public static int CYCLE_COUNT = 85469928;

    // 6-Vertex Samplings
    //AAPM
    public static double cycleEIGHTSample(int sample, BipartiteGraph graph){
        double final_result = 0;
        for(int i=0; i<sample; i++){
            Cycle cycle = graph.getRandomCycle();
            long count = ExactCount.cycleEIGHTCount(cycle, graph);

            final_result += count;
        }
        return (final_result/sample)*CYCLE_COUNT;
    }

    //ACNM
    public static double cycleNINESample(int k, BipartiteGraph graph){
        double final_result = 0;
        for(int i=0; i<k; i++){
            Cycle cycle = graph.getRandomCycle();
            long count = ExactCount.cycleNINECount(cycle, graph);

            final_result += count;
        }
        return final_result/k*CYCLE_COUNT;
    }

    //BBJM
    public static double cycleTENSample(int k, BipartiteGraph graph){
        double final_result = 0;
        for(int i=0; i<k; i++){
            Cycle cycle = graph.getRandomCycle();
            long count = ExactCount.cycleTENCount(cycle, graph);

            final_result += count;
        }
        return final_result/k*CYCLE_COUNT;
    }

    public static double cycleFOURSample(int sample, BipartiteGraph graph){
        double final_result = 0;
        for(int i=0; i<sample; i++){
            Cycle cycle = graph.getRandomCycle();
            long count = ExactCount.cycleFOURCount(cycle, graph);

            final_result += count;
        }
        return (final_result/sample)*CYCLE_COUNT;
    }

    public static double cycleFIVESample(int sample, BipartiteGraph graph){
        double final_result = 0;
        for(int i=0; i<sample; i++){
            Cycle cycle = graph.getRandomCycle();
            long count = ExactCount.cycleFIVECount(cycle, graph);

            final_result += count;
        }
        return (final_result/sample)*CYCLE_COUNT;
    }



}

