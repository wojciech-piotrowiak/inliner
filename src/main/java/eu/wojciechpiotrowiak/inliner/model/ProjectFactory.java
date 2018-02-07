package eu.wojciechpiotrowiak.inliner.model;

import java.util.List;

public class ProjectFactory {

    public static Project buildFromMethodInfo(List<MethodInformation> methodInformationList)
    {
        Project project = new Project(methodInformationList);
        project.printAllItems();
        return project;
    }
}
