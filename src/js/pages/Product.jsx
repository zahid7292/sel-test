import React from "react";

import { withStyles } from "@material-ui/core/styles";

import {
  TabbedHome,
  Create,
  Edit,
  List,
  Show,
  SimpleForm,
  SimpleShowLayout,
  TextInput,
  TextField,
  required,
  minLength,
  Datagrid,
  EditButton,
  ReferenceField
} from "jazasoft";

import {
  ReferenceInput,
  AutocompleteInput
} from "jazasoft/lib/mui/autocomplete";

const layout = "modern";

const createStyle = theme => ({});

export const CreateProduct = withStyles(createStyle)(
  ({ classes, ...props }) => {
    return (
      <Create layout={layout} {...props}>
        <SimpleForm className={classes.form} redirect="home">
          <ReferenceInput
            source="category.id"
            reference="categories"
            xs={12}
            validate={[required()]}
          >
            <AutocompleteInput />
          </ReferenceInput>
          <TextInput
            source="name"
            validate={[required(), minLength(2)]}
            xs={12}
          />
          <TextInput source="description" xs={12} />
        </SimpleForm>
      </Create>
    );
  }
);

const editStyle = theme => ({});

export const EditProduct = withStyles(editStyle)(({ classes, ...props }) => {
  return (
    <Edit layout={layout} {...props}>
      <SimpleForm
        className={classes.form}
        redirect="home"
        form="record-form-edit"
      >
        <ReferenceInput source="category.id" reference="categories" xs={12}>
          <AutocompleteInput />
        </ReferenceInput>
        <TextInput
          source="name"
          validate={[required(), minLength(2)]}
          xs={12}
        />
        <TextInput source="description" xs={12} />
      </SimpleForm>
    </Edit>
  );
});

const viewStyle = theme => ({});

export const ViewProduct = withStyles(viewStyle)(({ classes, ...props }) => {
  return (
    <Show layout={layout} {...props}>
      <SimpleShowLayout>
        <TextField source="name" />
        <ReferenceField source="category.id" reference="categories">
          <TextField source="name" />
        </ReferenceField>
        <TextField source="description" />
      </SimpleShowLayout>
    </Show>
  );
});

export const ProductLegacyHome = props => {
  return (
    <TabbedHome
      show={ViewProduct}
      create={CreateProduct}
      edit={EditProduct}
      {...props}
    />
  );
};

const homeStyle = theme => ({
  buttonEdit: {
    width: theme.spacing.unit * 14
  },
  buttonDelete: {
    width: theme.spacing.unit * 16
  }
});

export const ProductModernHome = withStyles(homeStyle)(
  ({ classes, ...props }) => {
    return (
      <List  {...props}>
        <Datagrid>
          <TextField source="name" />
          <ReferenceField
            label="Category"
            source="category.id"
            reference="categories"
            sortable={false}
          >
            <TextField source="name" />
          </ReferenceField>
          <TextField source="description" sortable={false} />
          <EditButton cellClassName={classes.buttonEdit} />
          {/* <DeleteButton cellClassName={classes.buttonDelete} /> */}
        </Datagrid>
      </List>
    );
  }
);
