package com.example.batch.springBatch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class HelloJobConfiguration {


	@Bean
	public Job helloJob(Step helloStep, JobRepository jobRepository){
		log.info("helloJob");
		return new JobBuilder("helloJob", jobRepository)
			.start(helloStep)
			.build();
	}

	@Bean
	public Step helloStep(JobRepository jobRepository, Tasklet testTasklet, PlatformTransactionManager platformTransactionManager){
		return new StepBuilder("helloStep1", jobRepository).tasklet(testTasklet, platformTransactionManager).build();
	}

	@Bean
	public Tasklet testTasklet(){
		return (((contribution, chunkContext) -> {
			log.info(">>>>>>>>This is Step1");
			return RepeatStatus.FINISHED;
		}));
	}
}
