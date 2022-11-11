package test.drools.model;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;

import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import java.util.Collection;


public class Drools5xTest {

//    public static void main(String[] args) {
//        Drools5xTest test = new Drools5xTest();
//        test.oldExecuteDrools();
//    }
//
//    private void oldExecuteDrools() {
//        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
//        kbuilder.add(ResourceFactory.newClassPathResource("com/rules/Rules.drl",
//                this.getClass()), ResourceType.DRL);
//        if (kbuilder.hasErrors()) {
//            System.out.println(kbuilder.getErrors().toString());
//        }
//        Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();
//        // add the package to a rulebase
//        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
//        // 将 KnowledgePackage 集合添加到 KnowledgeBase 当中
//        kbase.addKnowledgePackages(pkgs);
//        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
//        Product product = new Product();
//        product.setType(Product.GOLD);
//        ksession.insert(product);
//        ksession.fireAllRules();
//        ksession.dispose();
//        System.out.println("The discount for the product " + product.getType()
//                + " is " + product.getDiscount() + "%");
//    }

    @Test
    public void testRules() {
        // 构建 KieServices
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        // 获取 kmodule.xml 中配置中名称为 ksession-rule 的 session，默认为有状态的。
        KieSession kSession = kieContainer.newKieSession("ksession-rule");
        Product product = new Product();
        product.setType(Product.GOLD);
        kSession.insert(product);
        int count = kSession.fireAllRules();
        System.out.println("命中了" + count + "条规则！");
        System.out.println(" 商 品 " +product.getType() + " 的 商 品 折 扣 为 " +
                product.getDiscount() + "%。");
    }
}
