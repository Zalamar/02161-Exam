package project_management.test;

import cucumber.api.java.en.Given;
import project_management.app.Employee;
import project_management.app.ManagementTool;

import java.util.GregorianCalendar;
import java.util.List;

public class SeeAvailableWorkersOnOtherProjectsSteps {
    private ManagementTool managementTool;
    private List<List<String>> availableWorkers;

    public SeeAvailableWorkersOnOtherProjectsSteps(ManagementTool managementTool) {
        this.managementTool = managementTool;
    }

    @Given("^a project manager adds the following workers to the project$")
    public void aProjectManagerTheFollowingWorkersToTheProject(List<List<String>> testLines) throws Exception {
        for (List<String> line : testLines) {
            managementTool.addWorkerToProject(line.get(0));
        }
    }

    @Given("^a project manager adds the following workers to the activity$")
    public void aProjectManagerAddsTheFollowingWorkersToTheActivity(List<List<String>> testLines) throws Exception {
        for (List<String> line : testLines) {
            managementTool.addWorkerToActivity(line.get(0));
        }
    }
}
