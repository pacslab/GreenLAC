% workload

f1 = figure;
t = 1:1:600;
plot(t,req_load_1,'LineWidth',2, 'color', 'k');
%grid;
title('(a)','FontSize',18);
%set(gca,'xtick',[0:500:6500],'FontSize',15)
set(gca, 'XTickLabel', [])
xlim([0 600])
ylabel('Requests/s','FontSize',18) ;
f1.Position = [10 10 900 250]; 
print -depsc req_load_1


% av and 95th
f2 = figure;

plot(t,req_av_1,'-', 'color', 'r','MarkerIndices',250:1000:length(req_av_1), 'MarkerSize',8,'LineWidth',2);
hold on
plot(t,req_95th_1,'--', 'color', 'b','MarkerIndices',250:1000:6500, 'MarkerSize',8,'LineWidth',2);
hold off
hold on
%grid;
%set(gca,'xtick',[0:500:6500],'FontSize',15)
set(gca, 'XTickLabel', [])
xlim([0 600])
title('(b)','FontSize',18);
a = legend( 'Average Response Time', '95th Percentile')
ylabel('Response Time (ms)','FontSize',18) 
ylim([0 700])
set(a,'location','best')


f2.Position = [10 10 900 250]; 
print -depsc response_time_1

hold off


% cpu
f3 = figure;
plot(t,cpu_1,'-', 'color', 'm','MarkerIndices',250:1000:6500,'MarkerSize',8,'LineWidth',2);
%grid;
%set(gca,'xtick',[0:500:6500],'FontSize',15)
xlim([0 600])
ylim([0 100])
title('(c)','FontSize',18);
ylabel('CPU Utilization (%)','FontSize',18) 
xlabel('Time (s)','FontSize',18) 
set(a,'location','best')

f3.Position = [10 10 900 250]; 
print -depsc cpu_1



% buffer
f3 = figure;
stem(buffer_1);
%grid;
%set(gca,'xtick',[0:500:6500],'FontSize',15)
xlim([0 600])
ylim([0 7])
title('(d)','FontSize',18);
ylabel('Buffer Size (req)','FontSize',18) 
xlabel('Time (s)','FontSize',18) 
set(a,'location','best')

f3.Position = [10 10 900 250]; 
print -depsc buffer_1

% error
f3 = figure;
plot(t,total_error_1,'-', 'color', 'g','MarkerSize',8,'LineWidth',2);

%grid;
%set(gca,'xtick',[0:500:6500],'FontSize',15)
xlim([0 600])
ylim([0 12])
title('(e)','FontSize',18);
ylabel('# of Rejected Requests','FontSize',18) 
xlabel('Time (s)','FontSize',18) 
set(a,'location','best')

f3.Position = [10 10 900 250]; 
print -depsc cpu_1

% bar chart number requests
f8 = figure;
value = [4321 5614];
X = categorical({'Edge EC2','AWS Core'});
X = reordercats(X,{'Edge EC2','AWS Core'});
b = bar(X,value, 0.5);
hold on

b.FaceColor = 'flat';

b.CData(1,:) = [ 20 20 20]/255; 
b.CData(2,:) =      [47 79 79] / 255;
set(gca,'FontSize',15,'YColor','k','TickLength',[0 0])



title('Request Distribution','FontSize',18);
ylabel('# of Requests','FontSize',18);



print -depsc requests_1
















