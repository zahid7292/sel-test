import englishMessages from "jazasoft/lib/i18n/default-messages/js-language-english";

// const englishMessages = i18n["js-language-english"];

export default {
  ...englishMessages,
  app_name_short: "Sample App",
  app_name_full: "Sample Application",
  dashboard: "Dashboard",
  taskTypes: {
    jo_create: "Create Job Objective for %{user} (%{jobTitle}).",
    jo_update_promoted: "%{user} has been promoted to %{jobTitle}. Update Job Objective.",
    jo_approval: "JO Approval for %{user} (%{jobTitle}) is pending by %{architect} (Architect).",
    jd_approval: "Approval for JD Template (%{jobTitle}) is pending by %{architect} (Architect).",
    self_assessment: "Your ‘Self-Assessment’ is pending: Due Month: %{dueMonth}",
    pre_assessment: "Pre-assessment of %{noOfEmployee} Employees is pending.",
    final_assessment: "Final-assessment of %{noOfEmployee} Employees is pending.",
    attend_assessment: "You need to attend Final Assessment of %{noOfEmployee} Employees this month.",
    recruitment: "Recruitment for %{jobTitle} is pending. (Recruited: %{recruited}, Vacancies: %{vacancies}, Posted on: %{createdAt}).",
    recruitment_hr: "Recruitment for %{jobTitle} is pending. (Recruited: %{recruited}, Vacancies: %{vacancies}, Posted by: %{createdBy}, Posted on: %{createdAt}).",
  },
  resources: {
    
    categories: {
      name: "Category |||| Categories",
      fields: {
        name: "Name",
        desc: "Description"
      }
    },
    products: {
      name: "Product |||| Products",
      fields: {
        name: "Product",
        'category.id': 'Category',
      }
    },
    users: {
      name: "User |||| Users",
      fileds: {
        name: "Name",
        username: "Username",
        email: "Email",
        mobile: "Mobile"
      }
    },
    test: {
      name: "Test Page"
    }
  }
};
