
$(function(){
		drag('reservationTitle');
		
	//预定
		
	//预定按钮 禁用
		var isReservationBtnDis=true;  
		var usertype = $("#usertype").val();
		//alert(usertype)
		if(usertype==3){
			isReservationBtnDis = false;			
		}
		else{
			isReservationBtnDis = true;
		}
		var oReservationBtn=$('#reservationBtn');
		////后台判断
		BtnDisabled(oReservationBtn,isReservationBtnDis);
	//预定按钮 禁用end
	
		$('#reservationBtn').click(function(){
			var $mask=$('<div id="mask"></div>');
			var wDoc=$(document).width();
			var hDoc=$(document).height();
			$mask.css({"width":wDoc,"height":hDoc});
			$mask.appendTo("body");
			var wWin=$('#reservationDiv').width();
			var lWin=parseInt((wDoc-wWin)/2);		
			$('#reservationDiv').css("left",lWin);
			$('#reservationDiv').slideDown(200);
		//关闭
			$('#wClose').click(function(){
			$(this).parent().hide(200);
			$mask.remove();
			});
	
			$('#reservationCancel').click(function(){
			$('#reservationDiv').hide(200);
			$mask.remove();
			});
			
			//窗口调整
			$(window).resize(function(){
				var wDoc=$(document).width();
				var hDoc=$(document).height();
				$mask.css({"width":wDoc,"height":hDoc});
				var wWin=$('#reservationDiv').width();
				var lWin=parseInt((wDoc-wWin)/2);
				$('#reservationDiv').css("left",lWin);	
			});
			//窗口调整end
		});
	
	//选时间
			$("#timeGoRightBtn").click(function(){
	            $("#allTime option:checked").appendTo('#thisTime');
	        });
	        $("#timeGoLeftBtn").click(function(){
	            $("#thisTime option:checked").appendTo('#allTime');
	        });
			$("#thisTime option").dblclick(function(){
	        	$(this).appendTo('#allTime');//回不去  不知道why
	        }); 
	        $("#allTime option").dblclick(function(){
	        	$(this).appendTo('#thisTime');
	        });   
	//选时间end
	
	//选参会人员
			$("#participantsGoRightBtn").click(function(){
	            $("#allParticipants option:checked").appendTo('#thisParticipants');
	        });
	        $("#participantsGoLeftBtn").click(function(){
	            $("#thisParticipants option:checked").appendTo('#allParticipants');
	        });
	        $("#allParticipants option").dblclick(function(){
	        	$(this).appendTo('#thisParticipants');
	        });
			$("#thisParticipants option").dblclick(function(){
	        	$(this).appendTo('#allParticipants');
	        });      
	//选参会人员end
			
	//校验
			$('#reservationConfirm').click(function(){
				if($('#meetRoomSel').val()=='null'
				 ||$('#chooseResevDate').val()==''
				 ||$("#timeSelectContainer2 #thisTime").children("option").length==0
				 ||$("#participantsSelectContainer2 #thisParticipants").children("option").length==0
				 ||$('#chooseTime').val()=='null'
				 ||$('#meetName').val()==''
				 ||$('#meetContent').val()==''
				 ||$('#meetAgendaCont #meetAgenda').val()==''){
					alert("请完成未填选项！");
					return false;
				}
				else{
					$(".reservationForm").submit();
				}
		});
	
	
	//会议室详情弹窗
			$(function(){
				$.ajax({
					type:"POST",
					url:"RoomInfoServlet",
					data:$("#conferId").val(),
					dataType:"json",
					success:function(roomInfo){
						var $roomInfoDiv=$("#table tr .roomNum");
						$roomInfoDiv.each(function(index){
							$(this).mouseover(function(e){
								console.log(roomInfo);
								$(this).css("cursor","pointer");
								$(".roomInfo span:eq(0)").html(roomInfo[index].location);
								$(".roomInfo span:eq(1)").html(roomInfo[index].capacity);	
								$(".roomInfo span:eq(2)").html(roomInfo[index].equipment);				
								$(".roomInfo").show();
								$(".roomInfo").css({	
									"top": (e.pageY+10) + "px",
									"left": (e.pageX-5)  + "px"
									}).fadeIn(100);
								$(this).mousemove(function(e){
									$(".roomInfo").css({
									"top": (e.pageY+10) + "px",
									"left": (e.pageX-5)  + "px"
									});
								});
								$(this).mouseout(function(){
									$(".roomInfo").hide();
								});
					 		});
						});
					},
					error:function(){
						$(".roomInfo").html("请求失败！：（").css("color","red");
					}
				});
	
			});
		
	//会议室详情弹窗end
	
	
	
	//预定变红
			var list = $("#jsonarray").val();
			var room = eval(list);
			for(var num in room){		     	
			    var time=room[num].time.split(",");
			    for(var i=0;i<time.length-1;i++){		     	
			    	var j=parseInt(time[i]);
			    	var k=parseInt(room[num].roomNum)-1;
			     	$("#table tr:eq("+k+") td:eq("+j+")").addClass('backgroundRed');
			     	$("#table tr:eq("+k+") td:eq("+j+")").attr('data-conferName',room[num].conferName);
			     	$("#table tr:eq("+k+") td:eq("+j+")").attr('data-conferType',room[num].conferType);
			     	$("#table tr:eq("+k+") td:eq("+j+")").attr('data-conferTheme',room[num].conferTheme);
			     	$("#table tr:eq("+k+") td:eq("+j+")").attr('data-name',room[num].name);
			     	$("#table tr:eq("+k+") td:eq("+j+")").attr('data-bookingNum',room[num].bookingNum);
			     	$("#table tr:eq("+k+") td:eq("+j+")").attr('data-roomNum',room[num].roomNum);		    
	    			$("#table tr:eq("+k+") td:eq("+j+")").mouseover(function(e){
	    			    //后台调取数据    				
				    	var jsonInfo={
				    			'conferName':$(this).attr('data-conferName'),
				  		   		'conferType':$(this).attr('data-conferType'),
				  		   		'conferTheme':$(this).attr('data-conferTheme'),
				  	    	    'name':$(this).attr('data-name'),
				    	        'roomNum':$(this).attr('data-roomNum'),	
				    	        'bookingNum':$(this).attr('data-bookingNum')
				    			};
				    	$(".reservInfo span:eq(0)").html(jsonInfo.conferName);
						$(".reservInfo span:eq(1)").html(jsonInfo.conferType);	
						$(".reservInfo span:eq(2)").html(jsonInfo.conferTheme);	
						$(".reservInfo span:eq(3)").html(jsonInfo.name);	
						$(".reservInfo span:eq(4)").html(jsonInfo.roomNum);	
						$("#conferId").val(jsonInfo.bookingNum);
						$(".reservInfo").show();			    			
				    	$('.reservInfo')
				    	.css({	
				    		"top": (e.pageY+10) + "px",
				    		"left": (e.pageX-15)  + "px"
				    		}).fadeIn(100);
				    	$(this).mousemove(function(e){
					    	$(".reservInfo").css({
					    		"top": (e.pageY+10) + "px",
					    		"left": (e.pageX-15)  + "px"
					    	});
				    	});
				        	
				    });
			    }       
			}
	
			//会议详细信息 按钮禁用
			var oDetailedInfoBtn=$(".reservInfo #detailedInfo");
			var isDetailedInfoBtnDis=false;          
			BtnDisabled(oDetailedInfoBtn,isDetailedInfoBtnDis);                  
			//会议详细信息 按钮禁用end
			
			//取消预订按钮
			$("#cancelReservBtn").click(function(){
				if(confirm("确定取消此预定吗？")){
					$.ajax({
						type:"POST",
						url:"DeleteBookingServlet",
						data:{"conferId":$("#conferId").val()},
						success:function(){
							alert("取消成功！！！");
							location.reload();
						},
						error:function(){
							alert("不好意思，出错了。。。");
						}
					});
				}
				else{
					$(".reservInfo").hide();
				}
			});
			//取消预订按钮end
			
			$('#wClose1').click(function(){  //关闭
			$(".reservInfo").hide();
		});
	//预定详情悬浮弹窗end
	
	
	//按钮禁用函数
		function BtnDisabled(elem,isBtnDisabled){    
			if(isBtnDisabled){
				elem.attr("disabled", "disabled");//按钮禁用
				elem.addClass("btnDisabled");
			}
			else{
				elem.removeAttr("disabled");//解禁
			}
		};			
	//按钮禁用函数end
				
			
	$(function() {

	    $("#meetRoomSel").change(function() {
			var timeslot=["","8:00-9:00","9:00-10:00","10:00-11:00"," 11:00-12:00",
			              "12:00-13:00","13:00-14:00", "14:00-15:00","15:00-16:00",
			             "16:00-17:00",	"17:00-18:00","18:00-19:00"," 19:00-20:00",
			             "20:00-21:00", "21:00-22:00","22:00-23:00"];	    	
		   //提交的参数，name是和servlet中对应的接收变量
	       var params = {
	    	meetRoomSel : $("#meetRoomSel").val(),
	    	chooseResevDate : $("#chooseResevDate").val()
	       };
	       
	       var chooseDateTime = new Date(params.chooseResevDate).getTime();	//返回距 1970 年 1 月 1 日之间的毫秒数
	       var nowDate = new Date();
	       var nowDateTime = nowDate.getTime();
	       if(nowDateTime - 86400000 > chooseDateTime){		//若所选时间在当前日期之前，显示无可选时间段
	    	   timeslot = timeslot.slice(-1);
	    	   for(var i = 0; i < 16; i++){
	    		   timeslot.push("");
	    	   }	    	   
	       }else if(nowDateTime >= chooseDateTime){		//若所选时间为当前日期，显示可选时间段，其他（若所选时间在当前日期之后）显示全部时间段
	    	   var nowHour = nowDate.getHours();//0-23
		   	   if(nowHour >= 8){
			   		timeslot = timeslot.slice(nowHour - 7);
					for(var i = 0; i <= nowHour - 7; i++){
						timeslot.push("");
					}				
			   }	    	   
	       }

	       $.ajax({
	   			type: "POST",
	   			url: "AvailableTimeServlet",
	   			data: params,
	   			dataType:"json", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
	   			success: function(json){  
	   			//   var obj = $.parseJSON(json);  //使用这个方法解析json，返回一个json对象，形式应该是
	                                            //[“时间段1",“时间段2",“时间段3",“时间段4",“时间段5"]
		            $("#allTime").empty();//先清空
		            for(var data in json){
		            	$("#allTime").append("<option  value = "+json[data]+"> "+timeslot[parseInt(json[data])]+"</option>");
		            }	                   
	   			},
	   			error: function(json){
	   				//alert("json=" + json);
	   			 	return false;
	   			}
	       });
	    });
	});
	//同时提交两个表单
	
});

