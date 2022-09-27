//package back.jjowin.service;
//
//import back.jjowin.domain.Project;
//import back.jjowin.repository.ProjectRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class ProjectServiceTest {
//    @Autowired
//    ProjectService projectService;
//    @Autowired
//    ProjectRepository projectRepository;
//    @Autowired
//    EntityManager em;
//
//    @Test
////    @Rollback(false)
//    public void project_register(){
//        //given
//        Project project = new Project();
//        project.setName("창액공모전");
//        project.setCategory("공모전");
//
//        //when
//        Long id = projectService.register(project);
//
//        //then
//        assertEquals(project.getId(), id);
//    }
//
//}