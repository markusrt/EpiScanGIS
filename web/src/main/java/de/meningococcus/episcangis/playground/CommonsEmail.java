package de.meningococcus.episcangis.playground;

import javax.mail.URLName;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.sun.mail.smtp.SMTPSSLTransport;

public class CommonsEmail
{

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    SimpleEmail email = new SimpleEmail();
    email.setCharset(SimpleEmail.ISO_8859_1);
    email.setAuthentication("rzuw210", "Tbfriendz");
    email.setHostName("mailmaster.uni-wuerzburg.de");
    email.setDebug(true);
    try
    {
      email.addTo("mreinhardt@hygiene.uni-wuerzburg.de");
      email.setFrom("mreinhardt@hygiene.uni-wuerzburg.de");
      email.setMsg("Ölapaloma wüllst du Nißcht?");
      email.send();
    }
    catch (EmailException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
