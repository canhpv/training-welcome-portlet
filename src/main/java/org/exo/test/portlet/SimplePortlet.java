/*
 * Copyright (C) 2003-2012 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.exo.test.portlet;

import java.io.IOException;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

/**
 * Created by The eXo Platform SAS
 * Author : Canh Pham Van
 *          canhpv@exoplatform.com
 * Sep 13, 2012  
 */
public class SimplePortlet extends GenericPortlet{
  
  private static final String NORMAL_VIEW = "/view/normal.jsp";
  private static final String MAXIMIZED_VIEW = "/view/maximized.jsp";
  private static final String HELP_VIEW = "/view/help.jsp";
  
  private PortletRequestDispatcher normalView;
  private PortletRequestDispatcher maximizedView;
  private PortletRequestDispatcher helpView;
  
  public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException{
    if (WindowState.MINIMIZED.equals(request.getWindowState())){
      return;
    }
    
    if (WindowState.NORMAL.equals(request.getWindowState())){
      normalView.include(request, response);
    }else{
      maximizedView.include(request, response);
    }
  }
  
  protected void doHelp(RenderRequest request, RenderResponse response) throws PortletException, IOException{
    helpView.include(request, response);
  }
  
  public void init(PortletConfig config) throws PortletException{
    super.init(config);
    normalView = config.getPortletContext().getRequestDispatcher(NORMAL_VIEW);
    maximizedView = config.getPortletContext().getRequestDispatcher(MAXIMIZED_VIEW);
    helpView = config.getPortletContext().getRequestDispatcher(HELP_VIEW);
  }
  
  public void destroy(){
    normalView = null;
    maximizedView = null;
    helpView = null;
    super.destroy();
    
  }

}
