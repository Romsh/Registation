<%-- <%@ include file="css.jsp"%> --%>
<%-- <%@ include file="js.jsp"%> --%>

<div id="sidebar-wrapper">
	<nav id="spy">
		<ul class="sidebar-nav nav">
			<li class="sidebar-brand"><a href="#home"><span
					class="fa fa-home solo">Home</span></a></li>
			<li><a href="#anch1" data-toggle="collapse" data-target="#anch1">
					<span class="fa fa-anchor solo">Document</span>
			</a>
				<div class="collapse" id="anch1" aria-expanded="false">
					<ul class="flex-column pl-2 nav">
						<li class="nav-item"><a class="nav-link" href="#"><span
								class="text-success">Orders</span></a></li>
						<li class="nav-item"><a class="nav-link collapsed py-1"
							href="#submenu1sub1" data-toggle="collapse"
							data-target="#submenu1sub1"><span>Customers</span></a>
							<div class="collapse" id="submenu1sub1" aria-expanded="false">
								<ul class="flex-column nav pl-4">
									<li class="nav-item"><a class="nav-link p-1" href="#">
											<i class="fa fa-fw fa-clock-o"></i> Daily
									</a></li>
									<li class="nav-item"><a class="nav-link p-1" href="#">
											<i class="fa fa-fw fa-dashboard"></i> Dashboard
									</a></li>
									<li class="nav-item"><a class="nav-link p-1" href="#">
											<i class="fa fa-fw fa-bar-chart"></i> Charts
									</a></li>
									<li class="nav-item"><a class="nav-link p-1" href="#">
											<i class="fa fa-fw fa-compass"></i> Areas
									</a></li>
								</ul>
							</div></li>
					</ul>
				</div></li>
			<li><a href="#anch2"> <span class="fa fa-anchor solo">Mat�riel</span>
			</a></li>
			<li><a href="#anch3"> <span class="fa fa-anchor solo">Tableau
						de bord</span>
			</a></li>
			<li><a href="#anch4"> <span class="fa fa-anchor solo">Probl�mes</span>
			</a></li>
		</ul>
		<div id="testRole">Test ici</div>
	</nav>
</div>