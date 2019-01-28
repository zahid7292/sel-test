import React from "react";

import { withStyles } from "@material-ui/core/styles";

import {
  Create,
  Edit,
  List,
  SimpleForm,
  TextInput,
  TextField,
  required,
  minLength,
  Datagrid,
  EditButton,
} from "jazasoft";

const layout = "modern";

const createStyle = theme => ({});

export const CreateCategory = withStyles(createStyle)(
  ({ classes, ...props }) => {
    return (
      <Create layout={layout} {...props}>
        <SimpleForm className={classes.form} redirect="home">
          <TextInput source="name" validate={[required(), minLength(2)]} />
          <TextInput source="description" />
        </SimpleForm>
      </Create>
    );
  }
);

const editStyle = theme => ({});

export const EditCategory = withStyles(editStyle)(({ classes, ...props }) => {
  return (
    <Edit layout={layout} {...props}>
      <SimpleForm
        className={classes.form}
        redirect="home"
        form="record-form-edit"
      >
        <TextInput source="name" validate={[required(), minLength(2)]} />
        <TextInput source="description" />
      </SimpleForm>
    </Edit>
  );
});

const homeStyle = theme => ({
  buttonEdit: {
    width: theme.spacing.unit * 14
  },
  buttonDelete: {
    width: theme.spacing.unit * 16
  },
});

export const CategoryModernHome = withStyles(homeStyle)(
  ({ classes, ...props }) => {
    return (
      <List {...props}>
        <Datagrid>
          <TextField source="name" />
          <EditButton cellClassName={classes.buttonEdit} />
          {/* <DeleteButton cellClassName={classes.buttonDelete} /> */}
        </Datagrid>
      </List>
    );
  }
);
