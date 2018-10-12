package com.demo;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.Attachment;
import com.demo.model.AttachmentSummary;
import com.demo.model.MediaType;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationTests {

	@PersistenceContext
	private EntityManager manager;
	
	@Test
	public void testFetchingSubentity() throws IOException {
		
		InputStream file = getClass().getResourceAsStream("/Test.pdf");
		byte[] content = new byte[file.available()];
	    file.read(content);
		
		Attachment attachment = new Attachment();
		attachment.setName("Test Rafael Monteiro");
		attachment.setMediaType(MediaType.PDF);
		attachment.setContent(content);
		
		manager.persist(attachment);
		
		AttachmentSummary find = manager.createQuery(
				"from AttachmentSummary", AttachmentSummary.class)
				.setMaxResults(1)
				.getSingleResult();
		
		assertEquals("Test Rafael Monteiro", find.getName());
		assertEquals(MediaType.PDF, find.getMediaType());
	}

}
