<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="main">
        <div class="container-fluid">
            <div class="row" id="front-projects">
            <c:forEach items="${frontProjects}" var="p">
                <div class="col-md-4">
                    <div>
                        <a href="/Project/${p.getId()}"><h2>${p.getTitle()}</h2></a>
                    </div>
                </div>
            </c:forEach>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum consectetur, nulla vitae facilisis interdum, tortor est sagittis ligula, vel porta nibh nulla sed arcu. Vivamus a efficitur nisl. Nam vitae tincidunt magna, in tempus lacus. Fusce in volutpat elit, in faucibus nibh. Suspendisse feugiat mollis ante bibendum maximus. Ut vitae elit quis augue iaculis egestas vel at enim. Sed nunc odio, mollis non elementum ac, gravida quis neque. Fusce finibus pretium aliquam.
                        <br>
                        In hac habitasse platea dictumst. Phasellus ut vehicula erat. Sed tristique egestas orci sit amet suscipit. Nullam urna lectus, finibus in rhoncus id, porttitor in dolor. Maecenas sit amet eros nec mi posuere blandit. Nunc sagittis elit non commodo commodo. Etiam at tempor felis, eget facilisis enim. Duis ultrices, nibh eu pulvinar maximus, purus odio porta urna, at consequat ipsum lorem a odio. Cras nec lorem dui. Suspendisse et odio auctor, semper purus sit amet, facilisis metus. Ut non enim erat. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed ut varius nulla. Integer volutpat non elit ac fringilla. Aliquam at massa in diam laoreet placerat. Aenean velit sem, fringilla dignissim sapien scelerisque, auctor condimentum tortor.
                        <br>
                        Etiam ornare leo non tellus porta venenatis. Sed non erat consequat, interdum est quis, finibus leo. Duis sit amet maximus risus. Sed lacus ante, venenatis a semper vel, semper ac tortor. Quisque eleifend nisi ac velit laoreet blandit. Nulla sed metus imperdiet, eleifend urna a, pellentesque odio. Cras consequat arcu eget ligula euismod bibendum. Proin mattis nisl eget venenatis ultricies. In fermentum consequat velit, sed tempus dui viverra ut. Sed eleifend urna ligula, eget consequat metus pharetra sed. Praesent sit amet convallis metus, eget auctor sapien.
                        <br>
                        Aenean vel augue vitae augue euismod fringilla. Sed fermentum diam eget diam pellentesque iaculis. Fusce congue augue et lorem lobortis feugiat. Nam eros felis, vulputate eget sapien auctor, varius tincidunt tortor. Curabitur porttitor risus sed libero gravida maximus. Praesent et nulla sed leo placerat malesuada vitae iaculis urna. Sed semper nec lacus vitae interdum. Donec orci nunc, ornare nec lectus vitae, tincidunt interdum elit. Vestibulum tincidunt nisi mi, dictum auctor leo suscipit sed.
                        <br>
                        Donec lacinia sapien eget tincidunt aliquet. Mauris blandit in augue ut varius. Duis eleifend dolor sed faucibus pharetra. Curabitur sit amet odio sapien. Curabitur ac finibus nibh. Ut id nunc in tortor porta facilisis in non tellus. Duis aliquet id ipsum nec pretium. Nulla facilisi. Praesent faucibus in arcu nec vehicula. Donec gravida libero non pretium pulvinar. Morbi eu est nec elit condimentum rhoncus et non est. Pellentesque pulvinar tellus egestas, dapibus sapien id, tincidunt risus. Cras pretium, sapien a pretium semper, massa ligula ultricies libero, eget ultricies eros odio sit amet nulla. Integer pharetra condimentum suscipit. Nulla ac venenatis est. Vivamus eu arcu vel lorem feugiat rhoncus.
                    </p>
                </div>
            </div>
        </div>
    </jsp:attribute>
</t:layout>