<html>
<body>
<h2>Jersey RESTful Web Application!</h2>
<p><a href="webresources/myresource">Jersey resource</a>
<p>Visit the <a href="https://jersey.dev.java.net">Project Jersey website</a>
for more information on Jersey!


	<h1>File Upload with Jersey</h1>

	<form action="webresources/photos" method="post"
		enctype="multipart/form-data">

		<p>
			Select a file : <input type="file" name="file" size="45" />
		</p>

		<input type="submit" value="Upload It" />
	</form>

</body>
</html>
