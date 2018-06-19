package fg.mylibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import fg.expandablerecyclerview.model.ExpandableBean;
import fg.expandablerecyclerview.view.ExpandableRecyclerView;
import fg.mylibrary.model.Company;
import fg.mylibrary.model.Department;
import fg.mylibrary.model.Employee;

public class MainActivity extends AppCompatActivity {

    private ExpandableRecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView =  findViewById(R.id.rv);
        List<ExpandableBean> companies = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Company company = new Company();
            company.name = "公司"+i;
            company.departments = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                Department d = new Department();
                d.employeeList = new ArrayList<>();
                d.name = i+"部门"+j;
                for (int k = 0; k < j; k++) {
                    Employee e = new Employee();
                    e.name = i+"  "+j+"员工"+k;
                    d.employeeList.add(e);
                }
                company.departments.add(d);
            }

            companies.add(company);

        }
        mRecyclerView.setDatas(companies,true);
    }
}
