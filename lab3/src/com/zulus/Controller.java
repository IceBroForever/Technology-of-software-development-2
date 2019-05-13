package com.zulus;

import com.zulus.fileProcessors.BaseFileProcessorStartegy;
import com.zulus.fileProcessors.TextToTextFileProcessorStrategy;

public class Controller {
    private ConsoleView view;
    private NumbersStore numbersStore;

    public Controller(ConsoleView view, NumbersStore numbersStore) {
        this.view = view;
        this.numbersStore = numbersStore;
    }

    public void run() {
        while (true) {
            int choose = view.getInput();
            BaseFileProcessorStartegy processingStrategy;
            switch (choose) {
                case 1: {
                    processingStrategy
                            = new TextToTextFileProcessorStrategy("input.txt", "output.txt");
                    break;
                }
//                case 2: {
//                    processingStrategy
//                            = new BinaryFilesNumberProcessingStrategy("input.dat", "output.dat");
//                    break;
//                }
//                case 3: {
//                    processingStrategy
//                            = new BinaryFileNumberProcessingStrategy("numbers.dat");
//                    break;
//                }
//                case 4: {
//                    processingStrategy
//                            = new TextToBinaryProcessingStrategy("input.txt", "output.dat");
//                    break;
//                }
//                case 5: {
//                    processingStrategy
//                            = new BinaryToTextProcessingStrategy("input.dat", "output.txt");
//                    break;
//                }
                default: {
                    view.printNotification("Please, choose between 1 and 5!");
                    continue;
                }
            }
            numbersStore.setStrategy(processingStrategy);
            try {
                numbersStore.fillRandom(-50, 50);
                numbersStore.process();
            } catch (Exception ex) {
                view.printNotification(ex.getMessage());
            }
        }
    }
}