% workload

f1 = figure;
t = 1:1:600;
plot(t,req_load_2,'LineWidth',2, 'color', 'k');
%grid;
title('(a)','FontSize',18);
%set(gca,'xtick',[0:500:6500],'FontSize',15)
set(gca, 'XTickLabel', [])
xlim([0 600])
ylabel('Requests/s','FontSize',18) ;
f1.Position = [10 10 900 250]; 
print -depsc req_load_1_pi


% av and 95th
f2 = figure;

plot(t,req_av_2/1000,'-', 'color', 'r','MarkerIndices',250:1000:length(req_av_1), 'MarkerSize',8,'LineWidth',2);
hold on
plot(t,req_95th_2/1000,'--', 'color', 'b','MarkerIndices',250:1000:6500, 'MarkerSize',8,'LineWidth',2);
hold off
hold on
%grid;
%set(gca,'xtick',[0:500:6500],'FontSize',15)
set(gca, 'XTickLabel', [])
xlim([0 600])
title('(b)','FontSize',18);
a = legend( 'Average Response Time', '95th Percentile')
ylabel('Response Time (s)','FontSize',18) 
ylim([0 30])
set(a,'location','best')


f2.Position = [10 10 900 250]; 
print -depsc response_time_1_pi

hold off


% cpu
f3 = figure;
plot(t,cpu_pi,'-', 'color', 'c','MarkerIndices',250:1000:6500,'MarkerSize',8,'LineWidth',2);
hold on
plot(t,cpu_ec2,'--', 'color', 'm','MarkerIndices',250:1000:6500,'MarkerSize',8,'LineWidth',2);
%grid;
a = legend( 'Edge Pi', 'Edge EC2')
%set(gca,'xtick',[0:500:6500],'FontSize',15)
xlim([0 600])
ylim([0 100])
title('(c)','FontSize',18);
ylabel('CPU Utilization (%)','FontSize',18) 
xlabel('Time (s)','FontSize',18) 
set(a,'location','best')

f3.Position = [10 10 900 250]; 
print -depsc cpu_1_pi


% bar chart number requests
pi = 103;
ec2 = 1069;
core = 302;
	
f8 = figure;
value = [pi ec2 core];
X = categorical({'Edge Pi','Edge EC2','Core'});
X = reordercats(X,{'Edge Pi','Edge EC2','Core'});
b = bar(X,value, 0.5);
hold on

b.FaceColor = 'flat';

b.CData(2,:) =      [47 79 79] / 255;
b.CData(2,:) = [ 20 20 20]/255; 
b.CData(3,:) =      [47 79 89] / 255;
set(gca,'FontSize',15,'YColor','k','TickLength',[0 0])



title('Request Distribution','FontSize',18);
ylabel('# of Requests','FontSize',18);


print -depsc requests_pi
















