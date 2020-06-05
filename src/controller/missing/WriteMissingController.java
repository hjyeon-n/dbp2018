package controller.missing;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Client;
import model.Missing;
import model.service.ClientManager;
import model.service.ClientNotFoundException;
import model.service.ExistingMissingException;
import model.service.MissingManager;

//���� ���ε带 ���� API�� ����ϱ� ����...
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
//���� �뷮 �ʰ��� ���� Exception Ŭ������ FileUploadBase Ŭ������ Inner Ŭ������ ó��
import org.apache.commons.fileupload.servlet.*;

@SuppressWarnings("serial")
public class WriteMissingController extends HttpServlet implements Controller {
    private static final Logger log = LoggerFactory.getLogger(WriteMissingController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {
    	
    	ServletContext context = request.getSession().getServletContext();
    	String postId = "";
    	String clientId = (String)request.getSession().getAttribute("clientId");
    	String missingName = "";
    	String missingType = "";
    	String missingAddr = "";
    	String missingDate = "";
    	String missingDetail = "";
    	String filename = "";
    	
    	boolean check = ServletFileUpload.isMultipartContent(request);
    	
    	if(check) {//���� ������ ���Ե� ���°� �´ٸ�
			String path = context.getRealPath("/images/missing");
			File dir = new File(path);
			if(!dir.exists()) dir.mkdir();
			//���۵� ������ ������ ���� ��θ� �����.
			
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
                //���� ���ۿ� ���� �⺻���� ���� Factory Ŭ������ �����Ѵ�.
                factory.setSizeThreshold(10 * 1024);
                //�޸𸮿� �ѹ��� ������ �������� ũ�⸦ �����Ѵ�.
                //10kb �� �޸𸮿� �����͸� �о� ���δ�.
                factory.setRepository(dir);
                //���۵� �������� ������ ������ �ӽ� ������ �����Ѵ�.                
    
                ServletFileUpload upload = new ServletFileUpload(factory);
                //Factory Ŭ������ ���� ���� ���ε� �Ǵ� ������ ó���� ��ü�� �����Ѵ�.
                upload.setSizeMax(10 * 1024 * 1024);
                //���ε� �� ������ �ִ� �뷮�� 10MB���� ���� ����Ѵ�.
                upload.setHeaderEncoding("EUC-KR");
                //���ε� �Ǵ� ������ ���ڵ��� �����Ѵ�.
                                
                List items = (List)upload.parseRequest(request);
                //upload ��ü�� ���۵Ǿ� �� ��� �����͸� Collection ��ü�� ��´�.
                for(int i = 0; i < items.size(); ++i) {
                	FileItem item = (FileItem)items.get(i);
                	//commons-fileupload�� ����Ͽ� ���۹����� 
                	//��� parameter�� FileItem Ŭ������ �ϳ��� ����ȴ�.
                	
                	String value = item.getString("euc-kr");
                	//�Ѿ�� ���� ���� �ѱ� ó���� �Ѵ�.
                	
                	if(item.isFormField()) {//�Ϲ� �� �����Ͷ��...                		
                		if(item.getFieldName().equals("missingName")) missingName = value;
                		else if(item.getFieldName().equals("missingType")) missingType = value;
                		else if(item.getFieldName().equals("missingAddr")) missingAddr = value;
                		else if(item.getFieldName().equals("missingDate")) missingDate = value;
                		else if(item.getFieldName().equals("missingDetail")) missingDetail = value;
                	}
                	else {//�����̶��...
                		if(item.getFieldName().equals("missingImg")) {
                		//key ���� picture�̸� ���� ������ �Ѵ�.
                			filename = item.getName();//���� �̸� ȹ�� (�ڵ� �ѱ� ó�� ��)
                			if(filename == null || filename.trim().length() == 0) continue;
                			//������ ���۵Ǿ� ���� �ʾҴٸ� �ǳ� �ڴ�.
                			filename = filename.substring(filename.lastIndexOf("\\") + 1);
                			//���� �̸��� ������ ��ü ��α��� �����ϱ� ������ �̸� �κи� �����ؾ� �Ѵ�.
                			//���� C:\Web_Java\aaa.gif��� �ϸ� aaa.gif�� �����ϱ� ���� �ڵ��̴�.
                			File file = new File(dir, filename);
                			item.write(file);
                			//������ upload ��ο� ������ �����Ѵ�.
                			//FileItem ��ü�� ���� �ٷ� ��� ������ �� �ִ�.
                		}
                	}
                }
			}catch(SizeLimitExceededException e) {
				//���ε� �Ǵ� ������ ũ�Ⱑ ������ �ִ� ũ�⸦ �ʰ��� �� �߻��ϴ� ����ó��
					e.printStackTrace();           
	        }catch(FileUploadException e) {
	            //���� ���ε�� ���õǾ� �߻��� �� �ִ� ���� ó��
	                e.printStackTrace();
	        }catch(Exception e) {            
	                e.printStackTrace();
	        }
    	
    	Client client = null;
		ClientManager cmanager = ClientManager.getInstance();
		
		try {
			client = cmanager.findClient(clientId);	// ����� ���� �˻�
		} catch (ClientNotFoundException e) {				
	        return "redirect:/user/login/form";
		}
		
		request.setAttribute("client", client);
    	
		Missing missing = new Missing(0, clientId, missingName, missingType, missingAddr, missingDate, missingDetail, filename);
		MissingManager missingManager = MissingManager.getInstance();
		missingManager.create(missing);
 
	    return "redirect:/missing/list";
        } else {
            request.setAttribute("postingFailed", true);
			request.setAttribute("missing", new Missing());
			return "/missing/writeForm.jsp";
        }
    }
}