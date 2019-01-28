import * as React from "react";

import MenuIcon from "@material-ui/icons/Menu";
import logo from "./asset/img/logo2.png";
import avatar from "./asset/img/faces/avatar-male.png";

import {
  App as JApp,
  Resource,
  createOAuth2Client,
  createRestClient
} from "jazasoft";
import englishMessage from "./i18n/en";

import {CategoryModernHome, CreateCategory, EditCategory} from "./pages/Category";
import {ProductModernHome, CreateProduct, EditProduct} from "./pages/Product";

const appId = "seltest";

const authProvider = createOAuth2Client(
  "http://localhost:8011",
  "Basic Y2xpZW50OnNlY3JldA==",
  appId
);
const dataProvider = createRestClient("http://localhost:8011/api", appId);
const i18nProvider = locale => {
  return englishMessage;
};



class App extends React.Component {
  render() {
    return (
      <JApp
        appId={appId}
        appName="SAMPLE APP"
        appNameShort=""
        authProvider={authProvider}
        dataProvider={dataProvider}
        i18nProvider={i18nProvider}
        logo={logo}
        avatar={avatar}
        dashboard={(props) => <div>Dashboard</div>}
      >
        <Resource
          name="categories"
          resource="categories"
          home={CategoryModernHome}
          create={CreateCategory}
          edit={EditCategory}
          icon={MenuIcon}
        />
        <Resource
          name="products"
          resource="products"
          home={ProductModernHome}
          create={CreateProduct}
          edit={EditProduct}
          icon={MenuIcon}
        />
      </JApp>
    );
  }
}

export default App;
