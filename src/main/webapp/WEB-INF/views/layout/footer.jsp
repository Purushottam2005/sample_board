<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<script type="text/javascript">
		$("#register").click(function(){
			location.href = "/user/add";
		});
		
		var path = location.pathname;
		
		$("ul.nav > li > a").each(function(){
			if($(this).attr("href") == path){
				$(this).parent().attr("class", "active");
			}
			
			if($(this).attr("href").indexOf("/board")){
				
			}
			
		});
		
		/* $("#goList")click(function(){
			location.href = "../list/1";
		}); */
		
		$(document).on("click", "#goList", function(){
			location.href = "../list/1";
		});
	</script>
</body>
</html>