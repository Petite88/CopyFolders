package com.mkyong.tasklet;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ProcessFolders implements Tasklet {

	private int numberDuplicates;

	public void setNumberDuplicates(int numberDuplicates) {
		this.numberDuplicates = numberDuplicates;
		System.out.println("number of Folders" + numberDuplicates);
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		RepeatStatus status=RepeatStatus.CONTINUABLE;
		Object counter = chunkContext.getStepContext().getAttribute("Contador");
		if (counter == null) {
			counter = 2; 
		}else {
			counter=(int)counter+1;
		}
		
		File srcDir = new File("C:/Users/lcmaz/Desktop/New folder");
		File destDir = new File("C:/Users/lcmaz/Desktop/New folder"+counter);
		FileUtils.copyDirectory(srcDir, destDir);
//		System.out.println("LA PRUEBA FUNCIONA");
		if ((int)counter == numberDuplicates) {
			status = RepeatStatus.FINISHED;
		}
		chunkContext.getStepContext().setAttribute("Contador", counter);
		return status;

	}

}