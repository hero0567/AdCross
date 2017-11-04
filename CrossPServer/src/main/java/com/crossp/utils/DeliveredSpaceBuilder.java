package com.crossp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.crossp.beans.DeliveredSpace;
import com.crossp.jpa.domain.AppItem;
import com.crossp.jpa.domain.AppItemArea;
import com.crossp.jpa.domain.AppSpace;
import com.crossp.jpa.domain.AppTemplate;
import com.crossp.jpa.domain.AppTemplateConf;

public class DeliveredSpaceBuilder {
	public DeliveredSpace build(AppSpace cpSpace) {
		DeliveredSpace ds = null;
		
		try {
			// copy data to a new dir
			File src = new File(cpSpace.getAppTemplate().getAppTconf().getZipPath());
			File des = new File(cpSpace.getAppTemplate().getAppTconf().getZipPath() + "." + cpSpace.getId());
			if (!des.exists()) {
				copyFolder(src, des);
			}
			
			// replace with item info in des folder
			for (AppItemArea ia: cpSpace.getAppTemplate().getItemAreas()) {
				AppItem item = ia.getAppItem();
//				private Long id;
//				private String name;
//				private String appId;
//				private String icon;
//				private String type;
//				private String description;
//				private long date = System.currentTimeMillis();
//				@ManyToOne(cascade = CascadeType.MERGE)
//				private User user;
//				@OneToMany(fetch = FetchType.LAZY)
//				List<AppEvent> events;
			}
			
			// zip dir
			zipDir(des);
			
			// construct DeliveredSpace
			ds = new DeliveredSpace();
//			ds.setName(cpSpace.getName());
//			ds.setPositon(cpSpace.getPosition());
			ds.setShowType(cpSpace.getShowType());
			ds.setTransparency(cpSpace.getTransparency());
			ds.setDownloadLink(genDownloadLink(des.getAbsolutePath() + ".zip"));
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return ds;
	}
	
	private String genDownloadLink(String relativeDir) {
		// gen http link
		return relativeDir;
	}
	
	private void copyFolder(File src, File des) throws IOException {
		if (src.isDirectory()) {
			// if directory not exists, create it
			if (!des.exists()) {
				des.mkdir();
				System.out.println("Directory copied from " + src + "  to "
						+ des);
			}

			// list all the directory contents
			String files[] = src.list();

			for (String file : files) {
				// construct the src and dest file structure
				File srcFile = new File(src, file);
				File destFile = new File(des, file);
				// recursive copy
				copyFolder(srcFile, destFile);
			}

		} else {
			// if file, then copy it
			// Use bytes stream to support all file types
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(des);

			byte[] buffer = new byte[1024];

			int length;
			// copy the file content in bytes
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}

			in.close();
			out.close();
			System.out.println("File copied from " + src + " to " + des);
		}
	}
	
	private String zipDir(File dir) throws IOException {
		String zipFile = dir.getAbsolutePath() + ".zip";
		
		try {
			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			addDirToArchive(zos, dir.getAbsolutePath(), dir);
			// close the ZipOutputStream
			zos.close();
			fos.close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
		
		return zipFile;
	}
	
	private void addDirToArchive(ZipOutputStream zos, String baseDir, File srcFile) throws IOException {
		File[] files = srcFile.listFiles();
		System.out.println("Adding directory: " + srcFile.getAbsolutePath());
		for (int i = 0; i < files.length; i++) {
			// if the file is directory, use recursion
			if (files[i].isDirectory()) {
				addDirToArchive(zos, baseDir, files[i]);
				continue;
			}
			try {
				System.out.println("tAdding file: " + files[i].getAbsolutePath());
				// create byte buffer
				byte[] buffer = new byte[1024];
				FileInputStream fis = new FileInputStream(files[i]);
				String entryPath = files[i].getAbsolutePath().replace(baseDir, "");
				if (entryPath.startsWith("\\") || entryPath.startsWith("/")) {
					entryPath = entryPath.substring(1);
				}
				zos.putNextEntry(new ZipEntry(entryPath));
				int length;
				while ((length = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, length);
				}
				zos.closeEntry();
				// close the InputStream
				fis.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
				throw ioe;
			}
		}

	}

	public static void main(String[] args) {	
		// mock AppSpace
		AppSpace cpSpace = new AppSpace();
		cpSpace.setId(12345678L);
		cpSpace.setName("mock App Space");
		cpSpace.setPosition("10, 10, 80, 80");
//		cpSpace.setShowType("2");
		cpSpace.setTransparency("0.2");
		cpSpace.setPublic(true);

		AppTemplate appTemplate = new AppTemplate();
		AppTemplateConf appTmpConf = new AppTemplateConf();
		appTmpConf.setName("mock template");
		appTmpConf.setSize(1);
		appTmpConf.setZipPath("f:/tmp/CpSpace");
		appTemplate.setAppTconf(appTmpConf);
		
		AppItemArea area = new AppItemArea();
		area.setSequence(0);
		area.setStatus(1);
		area.setPublic(true);
		area.setAppItem(null);
		ArrayList<AppItemArea> areaItemList = new ArrayList<AppItemArea>();
		areaItemList.add(area);
		appTemplate.setItemAreas(areaItemList);
		
		cpSpace.setAppTemplate(appTemplate);
		DeliveredSpaceBuilder builder = new DeliveredSpaceBuilder();
		DeliveredSpace ds = builder.build(cpSpace);
		System.out.println("link: " + ds.getDownloadLink());
	}
}
