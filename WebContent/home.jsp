<html>
	<head>
		<title>Home</title>
		<style>
			body{
			background-color:gray;
			}
			input[type=image]{
			border-radius:15px;
			float:left;
			height: 24px;
			width:24px;
			border:3px solid black;
			outline:none;
			}
			input[type=image]:hover{
			background-color:gray;
			border:3px solid black;
			}
			#header{
			border:2px solid darkorange;
			border-radius:8px;
			background-color:white;
			float:left;
			padding-bottom:10px;
			width:100%;
			}
			h1 span{
			font-famil:verdana;
			background-color:darkorange;
			color:black;
			margin-left:20px;
			float:left;
			display:inline;
			border-radius:15px;
			padding-left:5px;
			padding-left:5px;
			}
			.searchinputbox {
				text-align:center;
				border: 2px solid darkorange;
				border-radius:8px;
				color:black;
				margin-left:20px;
				height: 22px;
				padding:15px;
				text-align: center;
				align: middle;
				width:50%;
				float:left;
			}
			.searchinputbox:focus{
			border:3px solid black;
			background-color:orange;
			color:black;
			outline:none;
			}
			#user{
			display:inline;
			float:right;
			width:50px;
			height:50px;
			margin-right:10px;
			}
			#left{
			text-align:center;
			width:30%;
			padding:10px;
			float:left;
			}
			#content{
			z-index:0;
			text-align:center;
			width:70%;
			padding:10px;
			float:left;
			}
			*{
			box-sizing:border-box;
			}
			td{
			border:3px solid black;
			padding:15px;
			}
			td a{
			color:orange;
			text-decoration:none;
			font-family:bookman,georgia;
			display:block;
			}
			td:hover{
			background-color:black;
			border:4px solid darkorange;
			color:white;
			font-family:Verdana;
			font-size:20px;			
			}
			#userinfo{
			display:inline;
			color:darkorange;
			float:right;
			padding-right:10px;
			font-size:20px;
			}
			form{
			display:inline;
			}
		</style>
	</head>
	<body>
		<div id="header">
		<h1><span>A-mail<span></h1>
		<form action="index.html" method="get">
			<input type="text" onfocus="if(this.value=='Search') this.value='';" onblur="if(this.value=='') this.value='Search';" value="Search" size="20" class="searchinputbox" alt="Search" id="mod_search_searchword" name="searchword"><!--maxlength=20 -->
			<input type="image" value="submit" src="search2.png" alt="submit Button">
		</form>

		<p id="userinfo">Hi ${email}</p>
		<form action="user">
			<input title="USER INFO/CHANGE PASSWORD" type="image" id="user" value="select" src="harry-potter.png" alt="user">
		</form>
		</div>
		<div id="row">
			<div id="left">
				<table width="100%" align="center">
					<tr><td><a href="compose" target="iframe1">COMPOSE</a></td></tr>
					<tr><td><a href="inbox" target="iframe1">INBOX</a></td></tr>
					<tr><td><a href="sent" target="iframe1">SENT</a></td></tr>
					<tr><td><a href="draft_display" target="iframe1">DRAFT</a></td></tr>
					<tr><td><a href="trash1" target="iframe1">TRASH</a></td></tr>
					<tr><td><a href="logout">LOGOUT</a></td></tr>
			</table>
			</div>
			<div id="content">
				<iframe src="inbox" width="100%" height="100%" name="iframe1" style="background-color:white"></iframe>
			</div>
		</div>

	</body>
</html>