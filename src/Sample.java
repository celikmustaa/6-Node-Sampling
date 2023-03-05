public class Sample {
    public static int CYCLE_COUNT = 85469928;

    // 6-Vertex Samplings

    public static double cycleFIVESample(int k, BipartiteGraph graph, boolean isInduced){
        double final_result = 0;
        for(int i=0; i<k; i++){
            Cycle cycle = graph.getRandomCycle();

            long count;
            if (isInduced){
                count = ExactCountInduced.cycleFIVECount(cycle, graph);
            }
            else {
                count = ExactCountNonInduced.cycleFIVECount(cycle, graph);
            }
            final_result += count;
        }
        return final_result/k*CYCLE_COUNT / 3;
    }

    //IGFK-92
    public static double cycleSIXSample(int k, BipartiteGraph graph, boolean isInduced){
        double final_result = 0;
        for(int i=0; i<k; i++){
            Cycle cycle = graph.getRandomCycle();

            long count;
            if (isInduced){
                count = ExactCountInduced.cycleSIXCount(cycle, graph);
            }
            else {
                count = ExactCountNonInduced.cycleSIXCount(cycle, graph);
            }
            final_result += count;
        }
        return final_result/k*CYCLE_COUNT / 2;
    }

    //AGJG-102
    public static double cycleSEVENSample(int k, BipartiteGraph graph, boolean isInduced){
        double final_result = 0;
        for(int i=0; i<k; i++){
            Cycle cycle = graph.getRandomCycle();

            long count;
            if (isInduced){
                count = ExactCountInduced.cycleSEVENCount(cycle, graph);
            }
            else {
                count = ExactCountNonInduced.cycleSEVENCount(cycle, graph);
            }
            final_result += count;
        }
        return final_result/k*CYCLE_COUNT;
    }

    //AAPM-101
    public static double cycleEIGHTSample(int sample, BipartiteGraph graph, boolean isInduced){
        double final_result = 0;
        for(int i=0; i<sample; i++){
            Cycle cycle = graph.getRandomCycle();

            long count;
            if (isInduced){
                count = ExactCountInduced.cycleEIGHTCount(cycle, graph);
            }
            else {
                count = ExactCountNonInduced.cycleEIGHTCount(cycle, graph);
            }


            final_result += count;
        }
        return (final_result/sample)*CYCLE_COUNT;
    }

    //ACNM-103
    public static double cycleNINESample(int k, BipartiteGraph graph, boolean isInduced){
        double final_result = 0;
        for(int i=0; i<k; i++){
            Cycle cycle = graph.getRandomCycle();

            long count;
            if (isInduced){
                count = ExactCountInduced.cycleNINECount(cycle, graph);
            }
            else {
                count = ExactCountNonInduced.cycleNINECount(cycle, graph);
            }


            final_result += count;
        }
        return final_result/k*CYCLE_COUNT;
    }

    //BBJM-104
    public static double cycleTENSample(int k, BipartiteGraph graph, boolean isInduced){
        double final_result = 0;
        for(int i=0; i<k; i++){
            Cycle cycle = graph.getRandomCycle();

            long count;
            if (isInduced){
                count = ExactCountInduced.cycleTENCount(cycle, graph);
            }
            else {
                count = ExactCountNonInduced.cycleTENCount(cycle, graph);
            }

            final_result += count;
        }
        return final_result/k*CYCLE_COUNT;
    }

    //BBJM-104
    public static double cycleTENSampleSQL(int k, boolean isInduced){
        double final_result = 0;
        for(int i=0; i<k; i++){
            Cycle cycle = BipartiteGraph.getRandomCycleFromSQL();

            long count;
            if (isInduced){
                count = ExactCountInduced.cycleTENCount(cycle, null);
            }
            else {
                count = ExactCountNonInduced.cycleTENCountSQL(cycle);
            }

            final_result += count;
        }
        return final_result/k*CYCLE_COUNT;
    }

    // ABPM
//    public static double cycleFOURSample(int sample, BipartiteGraph graph){
//        double final_result = 0;
//        for(int i=0; i<sample; i++){
//            Cycle cycle = graph.getRandomCycle();
//            long count = ExactCount.cycleFOURCount(cycle, graph);
//
//            final_result += count;
//        }
//        return (final_result/sample)*CYCLE_COUNT;
//    }
//
    // BBNM
//    public static double cycleFIVESample(int sample, BipartiteGraph graph){
//        double final_result = 0;
//        for(int i=0; i<sample; i++){
//            Cycle cycle = graph.getRandomCycle();
//            long count = ExactCount.cycleFIVECount(cycle, graph);
//
//            final_result += count;
//        }
//        return (final_result/sample)*CYCLE_COUNT;
//    }



}

