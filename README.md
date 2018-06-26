# aMail

Auburn Mail.

This project is a web application implementing an e-mail system. The server side coding is done using advanced java concepts like servlets,jsp,struts and jdbc.

This e-mail system finds its way different from others with some out of the box features. It not only has the basic necessary features of a mailing system like - compose,inbox,sent items,draft messages ,trash ,reply, user information,security changes,edit profile,search options,etc. but also have some advanced features like the following:-

1.A sent mail is marked as delivered/read based on whether the person(recepient of the mail) has read it or not.If he had read it the table column of status of sent mail will turn green from red(which indicated delivered mail)

2.Reminder system - Every mail is marked with an expiry date. After that expiry date is reached and the mail is still not read by the recepient , a notification will be generated for the mail on the recepient end giving the person 4 options - either to snooze the reminder and advance the expiry date ;or to open the message and read it;or to turn off the reminder on that mail ;or to delete the mail permanently.

3.File Upload is done via new inbuilt Servlet 3 specs feature which was earlier done by Apache Common FileUpload API and Apache common IO API.

4.While composing a mail can be marked with either of the two options - a)Urgent b)Casual . The recepient inbox will be differently styled for the urgent marked emails

5.A sender can remind the recepient twice to read his mail by giving a waiver.

(Features to be added lately - A learning algorithm is to be built and integrated with the mailing platform to detect and mark mails which are SPAM and which are NOT)
