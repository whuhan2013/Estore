package com.zj.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.zj.domain.Product;
import com.zj.domain.UploadMsg;
import com.zj.factory.BasicFactory;
import com.zj.service.ProdService;
import com.zj.util.IOUtils;
import com.zj.util.PicUtils;

public class AddprodServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(final HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		ProdService service=BasicFactory.getFactory().getService(ProdService.class);
		
		try {
		String encode=this.getServletContext().getInitParameter("encode");
		Map<String , String > paramMap=new HashMap<String,String>();

		//�ϴ�ͼƬ
		DiskFileItemFactory factory=new DiskFileItemFactory();
		factory.setSizeThreshold(1024*100);
		factory.setRepository(new File(this.getServletContext().getRealPath("WEB-INF/temp")));
		
		ServletFileUpload fileUpload=new ServletFileUpload(factory);
		fileUpload.setHeaderEncoding(encode);
		//fileUpload.setFileSizeMax(1024*1024*1);
		//fileUpload.setSizeMax(1024*1024*10);
		fileUpload.setProgressListener(new ProgressListener() {
			Long beginTime = System.currentTimeMillis();
			@Override
			public void update(long bytesRead, long contentLength,  int items) {
				// TODO �Զ����ɵķ������
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				UploadMsg umsg = new UploadMsg();
				BigDecimal br = new BigDecimal(bytesRead).divide(new BigDecimal(1024),2,BigDecimal.ROUND_HALF_UP);
				BigDecimal cl = new BigDecimal(contentLength).divide(new BigDecimal(1024),2,BigDecimal.ROUND_HALF_UP);
				//ʣ���ֽ���
				BigDecimal ll = cl.subtract(br);
				//�ϴ��ٷֱ�
				BigDecimal per = br.multiply(new BigDecimal(100)).divide(cl,2,BigDecimal.ROUND_HALF_UP);
				umsg.setPer(per.toString());
				//�ϴ���ʱ
				Long nowTime = System.currentTimeMillis();
				Long useTime = (nowTime - beginTime)/1000;
				//�ϴ��ٶ�
				BigDecimal speed = new BigDecimal(0);
				if(useTime!=0){
					speed = br.divide(new BigDecimal(useTime),2,BigDecimal.ROUND_HALF_UP);
				}
				umsg.setSpeed(speed.toString());
				//����ʣ��ʱ��
				BigDecimal ltime = new BigDecimal(0);
				if(!speed.equals(new BigDecimal(0))){
					ltime = ll.divide(speed,0,BigDecimal.ROUND_HALF_UP);
				}
				umsg.setLtime(ltime.toString());
				request.getSession().setAttribute("umsg", umsg);
				
			}
		});
		if(!fileUpload.isMultipartContent(request))
		{
			throw new RuntimeException("��ʹ����ȷ�ı������ϴ�");
		}
		
		
			List<FileItem> list=fileUpload.parseRequest(request);
			for(FileItem item:list)
			{
				if(item.isFormField())
				{
					//��ͨ�ֶ�
					String name = item.getFieldName();
					String value = item.getString(encode);
					paramMap.put(name, value);
					
				}else
				{
					//�ļ��ϴ���
					String realname=item.getName();
					String arry[]=realname.split("\\\\");
					realname=arry[arry.length-1];
					String uuidname=UUID.randomUUID().toString()+"_"+realname;
					
					String hash=Integer.toHexString(uuidname.hashCode());
					String upload=this.getServletContext().getRealPath("WEB-INF/upload");
					String imgurl="/WEB-INF/upload";
					for(char c:hash.toCharArray())
					{
						upload+="/"+c;
						imgurl+="/"+c;
					}
					imgurl+="/"+uuidname;
					paramMap.put("imgurl", imgurl);
					File uploadFile=new File(upload);
					if(!uploadFile.exists())
					uploadFile.mkdirs();
					
					InputStream in=item.getInputStream();
					OutputStream out=new FileOutputStream(new File(upload,uuidname));
					
					IOUtils.In2Out(in, out);
					IOUtils.close(in, out);
					item.delete();
					
					//��������ͼ
					PicUtils picu=new PicUtils(this.getServletContext().getRealPath(imgurl));
					picu.resizeByHeight(140);
				}
			}
			
			
			
			//�����ݿ��������Ʒ
			Product prod=new Product();
			BeanUtils.populate(prod, paramMap);
			
			service.addProd(prod);
			
			
			//��ʾ�ɹ����ص���ҳ
			response.getWriter().write("��Ʒ��ӳɹ���3��ص���ҳ");
			response.setHeader("Refresh", "3;url=index.jsp");
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
