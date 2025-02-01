package com.javaweb.controller.admin.api;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.constant.SystemConstant;
import com.javaweb.dao.ICategoryDAO;
import com.javaweb.model.CategoryModel;
import com.javaweb.model.NewModel;
import com.javaweb.model.UserModel;
import com.javaweb.paging.PageRequest;
import com.javaweb.paging.Pageble;
import com.javaweb.service.ICategoryService;
import com.javaweb.service.INewService;
import com.javaweb.sort.Sorter;
import com.javaweb.utils.FormUtil;
import com.javaweb.utils.HttpUtil;
import com.javaweb.utils.SessionUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(urlPatterns = { "/api-admin-new" })
public class NewAPI extends HttpServlet {

	@JsonIgnoreProperties(ignoreUnknown = true)
	private static final long serialVersionUID = 1L;

	@Inject
	INewService newService;

	@Inject
	ICategoryService categoryService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		NewModel newModel = FormUtil.toModel(NewModel.class, request);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		if (newModel.getType().equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(newModel.getPage(), newModel.getMaxPageItem(), new Sorter(newModel.getSortName(), newModel.getSortBy()));
			newModel.setListResult(newService.findAllAndSort(pageble));
			newModel.setTotalItem(newService.getTotalItem());
			newModel.setTotalPage((int) Math.ceil((double) newModel.getTotalItem() / newModel.getMaxPageItem()));
		} else if (newModel.getType().equals(SystemConstant.EDIT)) {
			if (newModel.getId() != null) {
				newModel = newService.findOne(newModel.getId());
			}
		}
		mapper.writeValue(response.getOutputStream(), newModel);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
		newModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUsername());
//		try {
//			// Create a factory for disk-based file items
//			DiskFileItemFactory factory = new DiskFileItemFactory();
//			// Configure a repository (to ensure a secure temp location is used)
//			ServletContext servletContext = this.getServletConfig().getServletContext();
//			File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
//			factory.setRepository(repository);
//			// Create a new file upload handler
//			ServletFileUpload upload = new ServletFileUpload(factory);
//			// Parse the request
//			List<FileItem> items = upload.parseRequest(request);
//			Iterator<FileItem> files =  items.iterator();
//			while (files.hasNext()) {
//				FileItem item = files.next();
//				if(item.isFormField()) {
//
//				} else {
//					String fileName = item.getName();
//					if (fileName!= null &&!fileName.equals("")) {
//						break;
//					} else {
//						Path path = Paths.get(fileName);
//						String storePath = servletContext.getRealPath("/template/image");
//						File uploadFile = new File(storePath + "/" + newModel.getId());
//						newModel.setThumbnail(storePath + "/" + newModel.getId());
//						item.write(uploadFile);
//					}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		newModel = newService.save(newModel);
		mapper.writeValue(response.getOutputStream(), newModel);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		NewModel updateNew =  HttpUtil.of(request.getReader()).toModel(NewModel.class);
		updateNew.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUsername());
		updateNew = newService.update(updateNew);
		mapper.writeValue(response.getOutputStream(), updateNew);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
		newService.delete(newModel.getIds());
		mapper.writeValue(response.getOutputStream(), "{}");
	}
}
