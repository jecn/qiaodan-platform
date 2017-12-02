package org.safari.platform.common.filter;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 关键字过滤器
 * @author Alitalk
 * @date  2017-02-10
 */
public class StopWordsFilter implements Filter{
	
	private Set<String> keyWords = new HashSet<String>();

	@Override
	public void init(FilterConfig config) throws ServletException {
		String keys =  config.getInitParameter("keys");
        StringTokenizer tokenizer = new StringTokenizer(keys);
        String token = null;
        while (tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();
            if(token != null && token.length() > 0){
                keyWords.add(tokenizer.nextToken());
            }
        }
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		CharArrayWrapper responseWrapper = new CharArrayWrapper(
                (HttpServletResponse) response);
        // 调用请求资源（使用自己包装的 responseWrapper）
        chain.doFilter(request, responseWrapper);
        // 取得响应字符串
        String responseString = responseWrapper.toString();
        // 将需要替换的关键字用“**”替换掉
        Iterator<String> iter = keyWords.iterator();
        while (iter.hasNext()) {
            responseString = replace(responseString, iter.next(), "**");
        }
         
        // 修改响应头信息中的 Content-Length
        response.setContentLength(responseString.length());
        PrintWriter out = response.getWriter();
        out.write(responseString);
		
	}

	@Override
	public void destroy() {
		
	}

	/**
     * 将字符串中的所有的指定子字符串替换掉
     * @param mainString 需要进行替换的字符串
     * @param orig 需要被替换的子串
     * @param replacement 替换后的新串
     * @return 返回替换后的字符串
     */
    public static String replace(String mainString, String orig, String replacement) {
        String result = "";
        int oldIndex = 0;
        int index = 0;
        int origLength = orig.length();
        while ((index = mainString.indexOf(orig, oldIndex)) != -1) {
            result = result + mainString.substring(oldIndex, index) + replacement;
            oldIndex = index + origLength;
        }
        result = result + mainString.substring(oldIndex);
        return result;
    }

}


class CharArrayWrapper extends HttpServletResponseWrapper {
	
    private CharArrayWriter charWriter;
 
    /**
     * Initializes wrapper.
     * <P>
     * First, this constructor calls the parent constructor. That call is
     * crucial so that the response is stored and thus setHeader, *setStatus,
     * addCookie, and so forth work normally.
     * <P>
     * Second, this constructor creates a CharArrayWriter that will be used to
     * accumulate the response.
     */
    public CharArrayWrapper(HttpServletResponse response) {
        super(response);
        charWriter = new CharArrayWriter();
    }
 
    /**
     * When servlets or JSP pages ask for the Writer, don't give them the real
     * one. Instead, give them a version that writes into the character array.
     * The filter needs to send the contents of the array to the client (perhaps
     * after modifying it).
     */
    @Override
    public PrintWriter getWriter() {
        return new PrintWriter(charWriter);
    }
 
    /**
     * Get a String representation of the entire buffer.
     * <P>
     * Be sure <B>not</B> to call this method multiple times on the same
     * wrapper. The API for CharArrayWriter does not guarantee that it
     * "remembers" the previous value, so the call is likely to make a new
     * String every time.
     */
    @Override
    public String toString() {
        return charWriter.toString();
    }
 
    /** Get the underlying character array. */
    public char[] toCharArray() {
        return charWriter.toCharArray();
    }
}