import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.host.Element;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLElement;

public class Test {
	static String s = "";
	static String path = "";
	static ArrayList<Node> nodeList = new ArrayList<>();
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		accessByLogin();
		/*WebClient webClient = new WebClient();
		HtmlPage currentPage = webClient.getPage("https://stackoverflow.com/");
		System.out.println(currentPage.getTitleText());
		getAnchor(currentPage);
		domWithXPath(currentPage);
		getByName(currentPage);*/
	}
	
	public static void getByName(HtmlPage current){
		
		HtmlElement element = (HtmlElement) current.getDocumentElement().getLastChild();
		doSomething(element);
		/*DomNodeList<HtmlElement> list = element.getElementsByTagName(s);
		 for(HtmlElement read : list){
			 if(read.asText().contains("Điều trị các bệnh dị ứng")){
				 System.out.println(read.asXml());
			 }
		 }*/
		for(Node read:nodeList){
			HtmlElement elementN = (HtmlElement) read;
			/*System.out.println(elementN.asXml());
			System.out.println(elementN.getCanonicalXPath());*/
			
			path = elementN.getCanonicalXPath();
			int lastIdx = elementN.getCanonicalXPath().lastIndexOf("/");
			path = path.substring(0, lastIdx);
			if(path.lastIndexOf("]") == path.length()-1){
				path = path.substring(0, path.lastIndexOf("[")); 
			}
			System.out.println(path);
			
			try {
				domWithXPath(current, path);
			} catch (FailingHttpStatusCodeException | DOMException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("----------------------");
		}
		
		
	}
	public static void doSomething(Node node) {
	    // do something with the current node instead of System.out
		
		if(node.getTextContent().contains(new StringBuffer("Accessing the resquest paramters from a custom .tag file"))){
			/*System.out.println(node.getTextContent());
			System.out.println(node.getNodeName());
			System.out.println(node.toString());
			System.out.println("-------------------------");
			s = node.getNodeName();*/
			nodeList.add(node);
			
		}
	    
	    NodeList nodeList = node.getChildNodes();
	    for (int i = 0; i < nodeList.getLength(); i++) {
	        Node currentNode = nodeList.item(i);
	        if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
	            //calls this method for all the children which is Element
	            doSomething(currentNode);
	        }
	    }
	}
	public static void getAnchor(HtmlPage current){
		try {
			HtmlAnchor advancedSearchAn = current.getAnchorByText("Tìm với Google");
			current = advancedSearchAn.click();
			String xmlSource = advancedSearchAn.getCanonicalXPath();
			
			System.out.println(xmlSource);
		} catch (IOException e) {
			//// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void domWithXPath(HtmlPage current,String xpath) throws FailingHttpStatusCodeException, MalformedURLException, DOMException, IOException{
		List<HtmlElement> element = (List<HtmlElement>)current.getByXPath(xpath);
		try {
			for(HtmlElement readElement : element){
				DomNode result = readElement.getChildNodes().get(0);
					System.out.println(result.getAttributes().item(0).getNodeValue());
					System.out.println(result.getTextContent());
			}
		} catch (Exception e) {
			// TODO: handle exception
			return;
		}
		
		
		
	}
	
	public static void accessByLogin(){
		 try (WebClient webClient = new WebClient()) {

			  /*  HtmlPage page = (HtmlPage) webClient.getPage("https://ma-andover.myfollett.com/aspen/logon.do"); 
			    HtmlForm form = page.getFormByName("logonForm"); 
			    form.getInputByName("username").type("myUsername"); 
			    form.getInputByName("password").type("myPassword"); 

			    page = form.getInputByValue("Log On").click();

			    System.out.println(page.asText());*/
			 
			 	HtmlPage page = (HtmlPage) webClient.getPage("https://accounts.google.com/ServiceLogin/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=AddSession"); 
			    List<HtmlForm> listform = page.getForms();
			    HtmlForm form = listform.get(0);
			    form.getInputByName("identifier").type("dinhhuy0610@gmail.com"); 
			   /* form.getInputByName("password").type("myPassword"); */

			    page = form.getElementsByAttribute(arg0, null, null)

			    System.out.println(page.asText());
			  } catch (FailingHttpStatusCodeException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
