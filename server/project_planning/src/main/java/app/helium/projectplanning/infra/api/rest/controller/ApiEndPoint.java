package app.helium.projectplanning.infra.api.rest.controller;

public class ApiEndPoint {
    public final static String CREATE_NEW_ISSUE = "/projects/{project_id}/create/issue";
    public final static String CREATE_NEW_SPRINT = "/projects/{project_id}/create/sprint";
    public final static String CREATE_NEW_ISSUE_WITHIN_SPRINT = "/projects/{project_id}/sprint/{sprint_id}/create/issue/";
    public final static String ADD_ISSUE_TO_SPRINT = "/projects/{project_id}/sprint/{sprint_id}/add/issue/{issue_id}";
}
