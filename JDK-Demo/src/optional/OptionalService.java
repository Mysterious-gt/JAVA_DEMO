package optional;

import java.util.Optional;

/**
 * @Author: jerrylee
 * @Date: 2019/10/23 10:31 上午
 * @Desc: 通过optional类处理参数
 */
public class OptionalService {
    public static void presentDo(Company p){
        Optional<Company> opt=Optional.ofNullable(p);
        opt.ifPresent(System.out::println);
    }

    public static void filterDo(Company p){
        Optional<Company> opt=Optional.ofNullable(p);
        opt.filter(a->"Tony".equals(a.getOwner())).ifPresent(System.out::println);
    }

    public static void emptOptionDo(Company p){
        Optional<Company> opt=Optional.ofNullable(p);
        final Company company = opt.orElse(new Company("Unknown", "0000", "none"));
        System.out.println(company);
    }

    public static void emptOptionThrow(Company p){
        Optional<Company> opt=Optional.ofNullable(p);
        final Company company = opt.orElseThrow(RuntimeException::new);
        System.out.println(company);
    }

    public static void optionMapDo(Company p){
        Optional<Company> opt=Optional.ofNullable(p);
        final Optional<String> nameOpt = opt.map(a -> a.getName());
        nameOpt.ifPresent(System.out::println);
    }

    public static void optionFlatMapDo(Company p){
        Optional<Company> opt=Optional.ofNullable(p);
        final Optional<String> descOpt = opt.flatMap(Company::getDesc);
        descOpt.filter(a->a.startsWith("DESC:")).ifPresent(System.out::println);
    }
}
