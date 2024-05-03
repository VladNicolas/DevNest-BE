package com.nicoals.DevNestBE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nicoals.DevNestBE.model.Chat;
import com.nicoals.DevNestBE.model.Invitation;
import com.nicoals.DevNestBE.model.Project;
import com.nicoals.DevNestBE.model.User;
import com.nicoals.DevNestBE.request.InvitationRequest;
import com.nicoals.DevNestBE.response.MessageResponse;
import com.nicoals.DevNestBE.service.InvitationService;
import com.nicoals.DevNestBE.service.ProjectService;
import com.nicoals.DevNestBE.service.UserService;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private InvitationService invitationService;

    @GetMapping
    public ResponseEntity<List<Project>> getProjects(
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String tag,
        @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwt(jwt);
        List<Project> projects = projectService.getProjectByTeam(user, category, tag);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(
        @PathVariable Long projectId,
        @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Project project = projectService.getProjectById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Project> createProject(
        @RequestHeader("Authorization") String jwt,
        @RequestBody Project project
    ) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Project createdProject = projectService.createProject(project, user);
        return new ResponseEntity<>(createdProject, HttpStatus.OK);
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(
        @PathVariable Long projectId,
        @RequestHeader("Authorization") String jwt,
        @RequestBody Project project
    ) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Project updatedProject = projectService.updateProject(project, projectId);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<MessageResponse> deleteProject(
        @PathVariable Long projectId,
        @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwt(jwt);
        projectService.deleteProject(projectId, user.getId());
        MessageResponse response = new MessageResponse("Project deleted!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchProjects(
        @RequestParam(required = false) String keyword,
        @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwt(jwt);
        List<Project> projects = projectService.searchProjects(keyword, user);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}/chat")
    public ResponseEntity<Chat> getChatByProjectId(
        @PathVariable Long projectId,
        @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Chat chat = projectService.getChatByProjectId(projectId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @PostMapping("/invite")
    public ResponseEntity<MessageResponse> inviteProject(
        @RequestHeader("Authorization") String jwt,
        @RequestBody InvitationRequest invitationRequest,
        @RequestBody Project project
    ) throws Exception {
        User user = userService.findUserByJwt(jwt);
        invitationService.sendInvitation(invitationRequest.getEmail(), invitationRequest.getProjectId());
        MessageResponse response = new MessageResponse("User invited succesfully.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/accept_invitation")
    public ResponseEntity<Invitation> acceptInviteProject(
        @RequestHeader("Authorization") String jwt,
        @RequestParam String token,
        @RequestBody Project project
    ) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Invitation invitation = invitationService.acceptInvitation(token, user.getId());
        projectService.addUserToProject(invitation.getProjectId(), user.getId());
        return new ResponseEntity<>(invitation, HttpStatus.ACCEPTED);
    }
}