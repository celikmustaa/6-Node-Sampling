public class Sample {
    public static int CYCLE_COUNT = 85469928;
    public static int FOURPATH_COUNT = 1;
    // 6-Vertex Samplings

    //NBNM-52
    public static double cycleONESample(int k, BipartiteGraph graph, boolean isInduced){
        double final_result = 0;
        for(int i=0; i<k; i++){
            Cycle cycle = graph.getRandomCycle();

            long count;
            if (isInduced){
                count = 0; //ExactCountInduced.cycleONECount(cycle, graph);
            }
            else {
                count = ExactCountNonInduced.cycleONECount(cycle, graph);
            }
            final_result += count;
        }
        return final_result/k*CYCLE_COUNT / 9;
    }

    //ADPM-73
    public static double cycleTWOSample(int k, BipartiteGraph graph, boolean isInduced){
        double final_result = 0;
        for(int i=0; i<k; i++){
            Cycle cycle = graph.getRandomCycle();

            long count;
            if (isInduced){
                count = 0; //ExactCountInduced.cycleTWOCount(cycle, graph);
            }
            else {
                count = ExactCountNonInduced.cycleTWOCount(cycle, graph);
            }
            final_result += count;
        }
        return final_result/k*CYCLE_COUNT / 6;
    }

    //FBNM-74
    public static double cycleTHREESample(int k, BipartiteGraph graph, boolean isInduced){
        double final_result = 0;
        for(int i=0; i<k; i++){
            Cycle cycle = graph.getRandomCycle();

            long count;
            if (isInduced){
                count = 0; //ExactCountInduced.cycleTHREECount(cycle, graph);
            }
            else {
                count = ExactCountNonInduced.cycleTHREECount(cycle, graph);
            }
            final_result += count;
        }
        return final_result/k*CYCLE_COUNT / 3;
    }

    //ABPM-90
    public static double cycleFOURSample(int k, BipartiteGraph graph, boolean isInduced){
        double final_result = 0;
        for(int i=0; i<k; i++){
            Cycle cycle = graph.getRandomCycle();

            long count;
            if (isInduced){
                count = ExactCountInduced.cycleFOURCount(cycle, graph);
            }
            else {
                count = ExactCountNonInduced.cycleFOURCount(cycle, graph);
            }
            final_result += count;
        }
        return final_result/k*CYCLE_COUNT / 3;
    }

    //BBNM-91
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

    //BBJM-104
    public static double fourPathELEVENSample(int k, BipartiteGraph graph, boolean isInduced){
        double final_result = 0;
        for(int i=0; i<k; i++){
            FourPath fourPath = graph.getRandomFourPath();

            long count;
            if (isInduced){
                count = ExactCountInduced.fourPathELEVENCount(fourPath, graph);
            }
            else {
                count = ExactCountNonInduced.fourPathELEVENCount(fourPath, graph);
            }

            final_result += count;
        }
        return final_result/k*FOURPATH_COUNT;
    }

    //BBJM-104
    public static double fourPathTWELVESample(int k, BipartiteGraph graph, boolean isInduced){
        double final_result = 0;
        for(int i=0; i<k; i++){
            FourPath fourPath = graph.getRandomFourPath();

            long count;
            if (isInduced){
                count = ExactCountInduced.fourPathTWELVECount(fourPath, graph);
            }
            else {
                count = ExactCountNonInduced.fourPathTWELVECount(fourPath, graph);
            }

            final_result += count;
        }
        return final_result/k*FOURPATH_COUNT;
    }

    //BBJM-104
    public static double fourPathTHIRTEENSample(int k, BipartiteGraph graph, boolean isInduced){
        double final_result = 0;
        for(int i=0; i<k; i++){
            FourPath fourPath = graph.getRandomFourPath();

            long count;
            if (isInduced){
                count = ExactCountInduced.fourPathTHIRTEENCount(fourPath, graph);
            }
            else {
                count = ExactCountNonInduced.fourPathTHIRTEENCount(fourPath, graph);
            }

            final_result += count;
        }
        return final_result/k*FOURPATH_COUNT;
    }

    //BBJM-104
    public static double fourPathFOURTEENSample(int k, BipartiteGraph graph, boolean isInduced){
        double final_result = 0;
        for(int i=0; i<k; i++){
            FourPath fourPath = graph.getRandomFourPath();

            long count;
            if (isInduced){
                count = ExactCountInduced.fourPathFOURTEENCount(fourPath, graph);
            }
            else {
                count = ExactCountNonInduced.fourPathFOURTEENCount(fourPath, graph);
            }

            final_result += count;
        }
        return final_result/k*FOURPATH_COUNT;
    }

    //BBJM-104
    public static double fourPathFIFTEENSample(int k, BipartiteGraph graph, boolean isInduced){
        double final_result = 0;
        for(int i=0; i<k; i++){
            FourPath fourPath = graph.getRandomFourPath();

            long count;
            if (isInduced){
                count = ExactCountInduced.fourPathFIFTEENCount(fourPath, graph);
            }
            else {
                count = ExactCountNonInduced.fourPathFIFTEENCount(fourPath, graph);
            }

            final_result += count;
        }
        return final_result/k*FOURPATH_COUNT;
    }

    //BBJM-104
    public static double fourPathSIXTEENSample(int k, BipartiteGraph graph, boolean isInduced){
        double final_result = 0;
        for(int i=0; i<k; i++){
            FourPath fourPath = graph.getRandomFourPath();

            long count;
            if (isInduced){
                count = ExactCountInduced.fourPathSIXTEENCount(fourPath, graph);
            }
            else {
                count = ExactCountNonInduced.fourPathSIXTEENCount(fourPath, graph);
            }

            final_result += count;
        }
        return final_result/k*FOURPATH_COUNT;
    }

    //BBJM-104
    public static double fourPathSEVENTEENSample(int k, BipartiteGraph graph, boolean isInduced){
        double final_result = 0;
        for(int i=0; i<k; i++){
            FourPath fourPath = graph.getRandomFourPath();

            long count;
            if (isInduced){
                count = ExactCountInduced.fourPathSEVENTEENCount(fourPath, graph);
            }
            else {
                count = ExactCountNonInduced.fourPathSEVENTEENCount(fourPath, graph);
            }

            final_result += count;
        }
        return final_result/k*FOURPATH_COUNT;
    }

}

