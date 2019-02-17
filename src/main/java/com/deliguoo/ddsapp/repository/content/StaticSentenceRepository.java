package com.deliguoo.ddsapp.repository.content;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.deliguoo.ddsapp.common.Constants;
import com.deliguoo.ddsapp.vo.content.StaticSentence;
import com.deliguoo.ddsapp.vo.content.StaticSentenceList;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class StaticSentenceRepository {
	private static final Logger logger = LoggerFactory.getLogger(StaticSentenceRepository.class);
	 @Value("classpath:static-answers.json")
	 private Resource staticAnswersResource;
	 public static int sizeOfList;
	 public static List<StaticSentence> staticAnswersList;
	 
	 @PostConstruct
	 public void init() throws JsonParseException, JsonMappingException, IOException{
		 if (StringUtils.isEmpty(staticAnswersResource)) {
			 logger.error(Constants.MISS_STATIC_ANSWERS_JSON);
			 return;
		 }
		 StaticSentenceList ssl = new ObjectMapper().readValue(
				 staticAnswersResource.getInputStream(), StaticSentenceList.class);
	     staticAnswersList = ssl == null ? new ArrayList<StaticSentence>() : ssl.getAnswers();
	     if ((sizeOfList = staticAnswersList.size()) == 0) {
			 logger.error(Constants.BAD_STATIC_ANSWERS_JSON);
		 }
	 }
}
