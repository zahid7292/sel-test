package com.jazasoft.seltest;

import com.jazasoft.mtdb.AbstractApp;
import com.jazasoft.mtdb.Scheduler;
import com.jazasoft.mtdb.dto.License;
import com.jazasoft.mtdb.model.Tenant;
import com.jazasoft.mtdb.model.User;
import com.jazasoft.mtdb.service.ILicenseService;
import com.jazasoft.mtdb.service.TenantService;
import com.jazasoft.mtdb.service.UserService;
import com.jazasoft.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by mdzahidraza on 21/07/17.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.jazasoft.mtdb","com.jazasoft.seltest"})
public class App extends AbstractApp {

    private final Logger logger = LoggerFactory.getLogger(App.class);
    
    @Autowired
    private Scheduler scheduler;

    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner init(
            UserService userService,
            TenantService tenantService,
            ILicenseService licenseService
    ){
        return (args) -> {

            if (userService.count() == 0) {
                User user = new User("Master User", "master@jaza-soft.com", "8904360418", "master", "MASTER");
                userService.save(user);
            }
          scheduleTasks(tenantService, licenseService);
        };
    }


    @Override
    protected void addDozerMappings(List<String> list) {
        list.add("dozer_tenant.xml");
    }

    @Override
    protected void addMessageSourceBasenames(List<String> list) {
        super.addMessageSourceBasenames(list);
    }


    private void scheduleTasks(TenantService tenantService, ILicenseService licenseService) {
        logger.debug("scheduleTasks..");
        int todayMinsAfterMidnight = DateUtils.getMinutesSinceMidnight(new Date());

        /***
         * Task2: Check License daily  at 1.00 AM. Also at start of application
         ***/

        Runnable checkLicense = () -> {
            logger.debug("checkLicense");
            List<String> tenants = tenantService.findAll().stream().map(Tenant::getTenantId).collect(Collectors.toList());
            logger.debug("No. of tenants = {}", tenants.size());
            for (String tenant: tenants) {
                License license = licenseService.refreshLicense(tenant);
                if (license != null) {
                    logger.debug("Tenant = {}, Is License Active = {}", tenant, license.isActive());
                }
            }
        };
        scheduler.submit(checkLicense, 0L, TimeUnit.SECONDS);
        long scheduleAt = 1 * 60 + 0; // 1.00 AM
        long diff = scheduleAt - todayMinsAfterMidnight;
        long initialDelay = (diff > 0) ? diff : (24 * 60) - diff;
        scheduler.submit(checkLicense, initialDelay, 24*60L, TimeUnit.MINUTES);
//
//
//        /***
//         * Task2: Database backup
//         ***/
//
//        Runnable dbBackupTask = () -> {
//            LOGGER.info("Database backup started...");
//            List<String> databaseList = tenantService.findAll().stream().map(Company::getDbName).collect(Collectors.toList());
//
//            File dir = new File(Utils.getAppHome() + File.separator + "bin");
//            String backupDir = Utils.getAppHome() + File.separator + "data" + File.separator + "db-backup" + File.separator;
//            String suffix = "_" + new SimpleDateFormat("ddMMMYY").format(new Date()) + ".sql";
//
//            try {
//                //Datasource details
//                String host = (String) Utils.getAppProperty("spring.datasource.host");
//                String port = (String) Utils.getAppProperty("spring.datasource.port");
//                String username = (String) Utils.getAppProperty("spring.datasource.username");
//                String password = (String) Utils.getAppProperty("spring.datasource.password");
//
//                String masterdb = (String) Utils.getAppProperty("spring.datasource.masterdb");
//                databaseList.add(masterdb);
//
//                Process process = null;
//                Map<String,Object> result = null;
//                for (String db: databaseList) {
//                    process = ProcessUtils.createProcess(dir, "/bin/bash","db_backup.sh", host, port, username, password, db, backupDir + db + suffix);
//                    result = ProcessUtils.execute(process);
//                    if ((Integer)result.get(ProcessUtils.EXIT_CODE) != 0) {
//                        LOGGER.error("Database backup of db = {} failed. error = [{}\n{}]", db, result.get(ProcessUtils.CONSOLE_OUTPUT), result.get(ProcessUtils.CONSOLE_ERROR));
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            LOGGER.info("Database backup completed.");
//        };
//        scheduleAt = 3 * 60 + 0; // 3.00 AM
//        diff = scheduleAt - todayMinsAfterMidnight;
//        initialDelay = (diff > 0) ? diff : (24 * 60) - diff;
//        Scheduler.getInstance().submit(dbBackupTask, initialDelay, 24*60L, TimeUnit.MINUTES);
    }
}
