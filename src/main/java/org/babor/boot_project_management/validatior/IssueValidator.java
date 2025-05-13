package org.babor.boot_project_management.validatior;

import lombok.RequiredArgsConstructor;
import org.babor.boot_project_management.exception.IssueNotFoundException;
import org.babor.boot_project_management.service.IssueService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IssueValidator {
    private final IssueService issueService;
    public void isPresent(int issueId) {
        if(issueService.findByIdCustom(issueId) == null) {
            throw new IssueNotFoundException("Issue with "+issueId +" does not exist!!!");
        }
    }

}
