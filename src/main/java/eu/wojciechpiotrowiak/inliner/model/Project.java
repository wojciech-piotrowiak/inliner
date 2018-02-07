package eu.wojciechpiotrowiak.inliner.model;

import java.util.List;

class Project {

    private List<MethodInformation> methodInformationList;

    private Project() {
    }

    Project(List<MethodInformation> methodInformationList) {
        this.methodInformationList = methodInformationList;
    }

    public void printAllItems() {
        methodInformationList.forEach(System.out::println);
    }
}
