import React from "react";
import { withStyles } from "@material-ui/core/styles";
import { List, TextField, Datagrid, EditButton } from "jazasoft";

const homeStyle = theme => ({
  buttonEdit: {
    width: theme.spacing.unit * 14
  },
});

export const CategoryModernHome = withStyles(homeStyle)(
  ({ classes, ...props }) => {
    return (
      <List {...props}>
        <Datagrid>
          <TextField source="name" />
          <EditButton cellClassName={classes.buttonEdit} />
        </Datagrid>
      </List>
    );
  }
);
