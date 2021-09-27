package llc.arma.checklisttag.data;import java.util.List;import java.util.stream.Collectors;public abstract class Mapper<I, O> {    public abstract O map(I input);    public List<O> map (List<I> input){        return input.stream().map(this::map).collect(Collectors.toList());    }}